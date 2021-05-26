public class test {
    public static void main(String[] args)
    {
        String val = "This is a car. This car is fas";
        String[] lines = val.split("\\.");
        for(int i = 0; i < lines.length; i++)
        {
            String[] tokens = lines[i].split("\\W+");
            for(int j = 0; j < tokens.length; j++)
            {
                if(j != tokens.length-1)
                {
                    System.out.println(tokens[j] + " " + tokens[j+1]);
                }
            }
        }
    }
}
