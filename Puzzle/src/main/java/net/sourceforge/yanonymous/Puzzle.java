/**
 *
 */

package net.sourceforge.yanonymous;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sourceforge.yanomsg.YanoMsg.Graph;
import net.sourceforge.yanomsg.YanoMsg.Graph.Edge;
import net.sourceforge.yanomsg.YanoMsg.Graph.Edge.Node;

@javax.servlet.annotation.WebServlet(name = "Puzzle", urlPatterns = {"/Puzzle"})
public class Puzzle extends HttpServlet {

  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_HOST = "localhost";
  static final String DB_PORT = "3306";
  static final String DB_NAME = "yanonymouspuzzle";
  static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
  static final String DB_USER;
  static final String DB_PASS;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    response.setCharacterEncoding("UTF-8");
    try (PrintWriter out = response.getWriter()) {
      String title = "YANonymous Puzzle";
      out.println("<!DOCTYPE html><html><head><title>");
      out.print(title);
      out.println("</title></head>");
      out.println("<body><h1 align=\"center\">");
      out.print(title);
      out.println("</h1><table border=\"1\" cellspacing=\"10\" cellpadding=\"5\" align=\"center\"><thead><tr>");
      out.println("<th>[ID] From node</th>");
      out.println("<th>Relationship</th>");
      out.println("<th>[ID] To node</th>");
      out.println("<th>Graph ID</th>");
      out.println("</tr></thead><tbody>");
      Class.forName(JDBC_DRIVER).newInstance();
      try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
        try (Statement statement = connection.createStatement()) {
          StringBuilder sql = new StringBuilder();
          sql.append("SELECT from_node.id, from_node.label, edge.label, to_node.id, to_node.label, graph.id");
          sql.append(" FROM edge");
          sql.append(" INNER JOIN node AS from_node ON from_node.id = edge.from_node_id");
          sql.append(" INNER JOIN node AS to_node ON to_node.id = edge.to_node_id");
          sql.append(" INNER JOIN graph ON graph.id = edge.graph_id");
          try (ResultSet resultSet = statement.executeQuery(sql.toString())) {
            while (resultSet.next()) {
              out.println("<tr>");
              String aID = resultSet.getString("from_node.id");
              String aLabel = resultSet.getString("from_node.Label");
              String relationshipLabel = resultSet.getString("edge.label");
              String bID = resultSet.getString("to_node.id");
              String bLabel = resultSet.getString("to_node.label");
              String graphLabel = resultSet.getString("graph.id");

              out.println("<td align=\"center\">" + "[" + aID + "] " + aLabel + "</td>");
              out.println("<td align=\"center\">" + relationshipLabel + "</td>");
              out.println("<td align=\"center\">" + "[" + bID + "] " + bLabel + "</td>");
              out.println("<td align=\"center\">[" + graphLabel + "]</td>");
              out.println("</tr>");
            }
          }
        }
      }
      out.println("</tbody></table>");
      out.println("</body>");
      out.println("</html>");
    } catch (Exception exception) {
      Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, exception.getMessage(), exception);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    try (PrintWriter out = response.getWriter()) {
      Graph graph = Graph.parseFrom(request.getInputStream());
      Class.forName(JDBC_DRIVER).newInstance();
      try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
        int graphID;
        if ((graphID = addGraph(connection)) != -1) {
          for (Graph.Edge edge : graph.getEdgeList()) {
            if (!addEdge(connection, graphID, edge)) {
              throw new Exception("Something wrong during edges processing.");
            }
          }
          out.println("OK");
        } else {
          throw new Exception("Something wrong during graph processing.");
        }
      } catch (Exception exception) {
        Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, exception.getMessage(),
            exception);
        out.println("ERROR");
      }
    } catch (Exception exception) {
      Logger.getLogger(Puzzle.class.getName()).log(Level.SEVERE, exception.getMessage(), exception);
    }
  }

  @Override
  public String getServletInfo() {
    return "desc";
  }

  private int addGraph(Connection connection) throws SQLException {
    int graphID = -1;
    try (PreparedStatement statement =
        connection.prepareStatement("INSERT INTO graph (id) VALUES (NULL)",
            Statement.RETURN_GENERATED_KEYS)) {
      if (statement.executeUpdate() > 0) {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
          graphID = generatedKeys.getInt(1);
        }
      }
    }
    return graphID;
  }

  private int addNode(Connection connection, Node node) throws SQLException {
    int nodeID = -1;
    try (PreparedStatement statement =
        connection.prepareStatement("INSERT INTO node (id, label) VALUES (NULL, ?)",
            Statement.RETURN_GENERATED_KEYS)) {
      if (node.hasLabel()) {
        statement.setString(1, node.getLabel());
      } else {
        statement.setNull(1, java.sql.Types.VARCHAR);
      }
      if (statement.executeUpdate() > 0) {
        ResultSet generatedKeys = statement.getGeneratedKeys();
        if (generatedKeys.next()) {
          nodeID = generatedKeys.getInt(1);
        }
      }
    }
    return nodeID;
  }

  private boolean addEdge(Connection connection, int graphID, Edge edge) throws SQLException {
    int fromID;
    int toID;

    if ((fromID = addNode(connection, edge.getFrom())) != -1) {
      if ((toID = addNode(connection, edge.getTo())) != -1) {
        try (PreparedStatement statement =
            connection
                .prepareStatement(
                    "INSERT INTO edge (id, from_node_id, label, to_node_id, graph_id) VALUES (NULL, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS)) {

          statement.setInt(1, fromID);
          if (edge.hasLabel()) {
            statement.setString(2, edge.getLabel());
          } else {
            statement.setNull(1, java.sql.Types.VARCHAR);
          }
          statement.setInt(3, toID);
          statement.setInt(4, graphID);
          statement.executeUpdate();
          return true;
        }
      }
    }
    return false;
  }
}
