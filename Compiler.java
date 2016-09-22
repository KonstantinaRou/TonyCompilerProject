public class Compiler
{
   public static void main(String []args) throws Exception
    {
        Process ps=Runtime.getRuntime().exec(new String[]{"java", "-jar", "jasmin"});
        ps.waitFor();
        java.io.InputStream is=ps.getInputStream();
        byte b[]=new byte[is.available()];
        is.read(b,0,b.length);
        System.out.println(new String(b));
    }
}