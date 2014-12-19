package net.sourceforge.yanonymous;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import static net.sourceforge.yanonymous.YourWorldView.YanoThemes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

interface Adder {
    int add(int a, int b);
}
class AdderImpl implements Adder {
    public int add(int a, int b) {
        return a + b;
    }
}

public class YanoXmlParser {
public static int jc =0;
    public static String[] beiras = {","};    
   
  public static ArrayList<YanoTheme> parseThemes(AssetManager assetmanager ,int i,int k,String[] neww,int sel)
      throws XmlPullParserException, IOException {
jc=-1;
    ArrayList<YanoTheme> YanoList = new ArrayList<YanoTheme>();

    String[] themeXmlFiles = assetmanager.list("themes");

    for (String themeXmlFile : themeXmlFiles) {
      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
      XmlPullParser xpp = factory.newPullParser();
       Adder adder = new AdderImpl();
        assert(adder.add(jc, 1) == jc++);
jc++;
      xpp.setInput(assetmanager.open("themes/" + themeXmlFile), null);
      int eventType = xpp.getEventType();

      YanoTheme theme = new YanoTheme();
      while (eventType != XmlPullParser.END_DOCUMENT) {
        if (eventType == XmlPullParser.START_TAG) {
          if (xpp.getName().equals("theme")) {
              String type2=    xpp.getAttributeValue(1);
              if(type2.equals("TEXT"))
              if(i==1 && sel ==jc){
                          for(int valami = 0 ;valami<k;valami++){
                          theme.setThemeName(neww[valami]);
                          theme.setThemeType("TEXT");
                          theme.addItem(neww[valami], Color.parseColor("#a489fcde"));}
                      i=0;
                      
                  }   
            String name = xpp.getAttributeValue(0);
            String type = xpp.getAttributeValue(1);
            String color = xpp.getAttributeValue(2);
            theme.setThemeName(name);
            theme.setThemeType(type);
            theme.addItem(name, Color.parseColor(color));
            if (type.equals("IMAGE")) {
 try {
              theme.addThemeImage(name, Drawable.createFromStream(
                  assetmanager.open("images/"
                      + name.toLowerCase().replace(' ', '_')
                      + "/"
                      + name.toLowerCase().replace(' ', '_')
                      + ".png"), null));
 }catch (IOException e) { ; }
             
            }
          
          }
          if (xpp.getName().equals("item")) {
            String name = xpp.getAttributeValue(0);
            String color = xpp.getAttributeValue(1);
            theme.addItem(name, Color.parseColor(color));
            if (theme.getThemeType().equals("IMAGE")) {
try{ 
              theme.addThemeImage(name, Drawable.createFromStream(
                  assetmanager.open("images/"
                      + theme.getThemeName().toLowerCase().replace(' ', '_')
                      + "/"
                      + name.toLowerCase().replace(' ', '_')
                      + ".png"), null));
} catch (IOException e) { ; }
            }
          }
        }

        eventType = xpp.next();
      }
      YanoList.add(theme);
    }

    return YanoList;
  }

  public static ArrayList<YanoRelationModel> parseRelations(AssetManager assetmanager)
      throws XmlPullParserException, IOException {

    ArrayList<YanoRelationModel> YanoRelList = new ArrayList<YanoRelationModel>();

    String[] relationXmlFiles = assetmanager.list("relations");

    for (String relationXmlFile : relationXmlFiles) {
      XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
      XmlPullParser xpp = factory.newPullParser();

      xpp.setInput(assetmanager.open("relations/" + relationXmlFile), null);
      int eventType = xpp.getEventType();

      YanoRelationModel rel = new YanoRelationModel();
      while (eventType != XmlPullParser.END_DOCUMENT) {
        if (eventType == XmlPullParser.START_TAG) {
          if (xpp.getName().equals("relation")) {
            String name = xpp.getAttributeValue(0);
            String color = xpp.getAttributeValue(1);
            rel.setRelationName(name);
            rel.addItem(name, Color.parseColor(color));
          }

          if (xpp.getName().equals("item")) {
            String name = xpp.getAttributeValue(0);
            String color = xpp.getAttributeValue(1);
            rel.addItem(name, Color.parseColor(color));
          }
        }

        eventType = xpp.next();
      }
      YanoRelList.add(rel);
    }

    return YanoRelList;
  }
}
