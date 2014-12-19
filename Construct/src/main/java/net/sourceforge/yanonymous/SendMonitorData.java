package net.sourceforge.yanonymous;

/**
 * Created by aximcore on 12/19/14.
 */

import net.sourceforge.yanonymous.YanoData.Datas;
import net.sourceforge.yanonymous.YanoData.Anon;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class SendMonitorData {
    private  List<Relation> relations;/* = new ArrayList<Relation>();*/
    private  List<Anonymous> anony;/* = new ArrayList<Anonymous>();*/

    Datas.Builder datas = Datas.newBuilder();

    public SendMonitorData(List<Anonymous> anony,List<Relation> rel){
        this.anony = anony;
        this.relations = rel;
        getAnony();
        getRel();

        sendData();
    }

    private void getAnony(){
        for ( Anonymous ano : anony){
            Anon.Builder a = Anon.newBuilder();
            if(ano.me)
                a.setIsMe(true);

            a.setX(ano.x);
            a.setY(ano.y);

            if ( ano.getTheme().equals("Politics") )
                switch (ano.getTheme()){
                    case "Fidesz":
                        a.setName(Anon.Name.Fidesz);
                        break;
                    case "MSZP":
                        a.setName(Anon.Name.MSZP);
                        break;
                    case "KDNP":
                        a.setName(Anon.Name.KDNP);
                        break;
                    case "Jobbik":
                        a.setName(Anon.Name.Jobbik);
                        break;
                    case "LMP":
                        a.setName(Anon.Name.LMP);
                        break;
                    case "DK":
                        a.setName(Anon.Name.DK);
                        break;
                    case "Együtt-PM":
                        a.setName(Anon.Name.EgyuttPM);
                        break;
                    default:
                        a.setName(Anon.Name.Yano);
                }

            datas.addAnons(a);
        }

    }

    private void getRel(){
        for ( Relation rel : relations ){
            YanoData.Relations.Builder r = YanoData.Relations.newBuilder();
            r.setX1(rel.nodeA.x);
            r.setY1(rel.nodeA.y);
            r.setX2(rel.nodeB.x);
            r.setY2(rel.nodeB.y);

            if (rel.getRelationTheme().equals("Family"))
                switch (rel.getRelationTheme()){
                    case "Mom":
                        r.setName(YanoData.Relations.Name.Mom);
                        break;
                    case "Dad":
                        r.setName(YanoData.Relations.Name.Dad);
                        break;
                    case "Sister":
                        r.setName(YanoData.Relations.Name.Sister);
                        break;
                    case "Brother":
                        r.setName(YanoData.Relations.Name.Brother);
                        break;
                    case "Wife":
                        r.setName(YanoData.Relations.Name.Wife);
                        break;
                    case "Husband":
                        r.setName(YanoData.Relations.Name.Husband);
                        break;
                    case "Daughter":
                        r.setName(YanoData.Relations.Name.Daughter);
                        break;
                    case "Son":
                        r.setName(YanoData.Relations.Name.Son);
                        break;
                    case "Grandma":
                        r.setName(YanoData.Relations.Name.Grandma);
                        break;
                    case "Grandpa":
                        r.setName(YanoData.Relations.Name.Grandpa);
                        break;
                    default:
                        r.setName(YanoData.Relations.Name.Family);
                }
            datas.addRelations(r);
        }

    }

    private void sendData(){
        try {
            Socket client = new Socket("192.168.1.103", 4444);

            datas.build().writeTo(client.getOutputStream());
            datas.clear();
            client.close();

        }catch (IOException io){
            System.out.println("Hiba: " + io.getMessage());
        }
    }
}
