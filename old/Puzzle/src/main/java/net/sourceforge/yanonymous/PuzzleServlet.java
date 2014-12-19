
/**
 *
 */
@javax.servlet.annotation.WebServlet(name = "PuzzleServlet", urlPatterns = {"/yano"})
public class PuzzleServlet
        extends javax.servlet.http.HttpServlet {

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req,
            javax.servlet.http.HttpServletResponse res)
            throws javax.servlet.ServletException, java.io.IOException {

        res.setContentType("text/html;charset=UTF-8");
        java.io.PrintWriter browser = res.getWriter();
        try {

            browser.println("<html>Hello, YANO!</html>");

            // send the social network
        } finally {
            browser.close();
        }
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req,
            javax.servlet.http.HttpServletResponse res)
            throws javax.servlet.ServletException, java.io.IOException {

        // receive community networks
    }

    @Override
    public String getServletInfo() {
        return "desc";
    }
}
