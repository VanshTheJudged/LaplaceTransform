import java.util.*;

public class Laplace {
    private Map<String, String> transforms;

    // Constructor to initialize the Laplace Transform rules
    public Laplace() {
        transforms = new HashMap<>();
        
        // Standard Laplace Transform Rules
        transforms.put("1", "1/s"); // Constant Function
        transforms.put("t^n", "n!/s^(n+1)"); // Power of t
        transforms.put("e^(at)", "1/(s-a)"); // Exponential Function
        transforms.put("sin(wt)", "w/(s^2 + w^2)"); // Sine Function
        transforms.put("cos(wt)", "s/(s^2 + w^2)"); // Cosine Function
        transforms.put("sinh(at)", "a/(s^2 - a^2)"); // Sinh Function
        transforms.put("cosh(at)", "s/(s^2 - a^2)"); // Cosh Function
        
        // The Laplace Transform of t * e^(at)
        transforms.put("t * e^(at)", "1/(s-a)^2"); // Key Formula
        
        transforms.put("t^n * e^(at)", "n!/(s-a)^(n+1)"); // Generalized t^n * e^(at)
        transforms.put("u(t)", "1/s"); // Unit Step Function
        transforms.put("delta(t)", "1"); // Delta Function
    }
    public String gettranform(String x){
        return transforms.getOrDefault(x, "Laplace not possible");
    }
    public String convolution(String x){
        String z="";
        String k="";
        int j= 0;
        StringBuilder sb1 = new StringBuilder(z);
        StringBuilder sb2 = new StringBuilder(k);
        for(int i =0;i<x.length();i++){
            if(j==0){
                if(x.charAt(i)=='*'){
                    j++;
                    continue;
                }
                sb1.append(x.charAt(i));
            }
            else{
                sb2.append(x.charAt(i));
            }
        }
        z=gettranform(sb1.toString());
        k=gettranform(sb2.toString());
        String result = z+"*"+k;
        return result;
    }
    public String Linearity(String x){
        int j= 0;
        int p=0;
        int z = 0;
        StringBuilder c1 = new StringBuilder();
        StringBuilder c2 = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for(int i =0;i<x.length();i++){
            if(j==0){
                if(x.charAt(i)=='+'){
                    j++;
                    continue;
                }
                else{
                    if((Character.isDigit(x.charAt(i)) || x.charAt(i) == '-') && p==0){
                        c1.append(x.charAt(i));
                    }
                    else{
                     sb1.append(x.charAt(i));
                     p++;
                    }
                }
            }
            
            else{
                if((Character.isDigit(x.charAt(i)) || x.charAt(i) == '-') && z==0){
                    c2.append(x.charAt(i));
                }
                else{
                    sb2.append(x.charAt(i));
                    z++;
                    }
                
            }
        }
        if(c1.length()==0){
            c1.append("1");
        }
        if(c2.length()==0){
            c2.append("1");
        }
        String result1 = (c1.toString().equals("1") ? "" : c1.toString()) + gettranform(sb1.toString());
        String result2 = (c2.toString().equals("1") ? "" : c2.toString()) + gettranform(sb2.toString());
        if(sb2.length()==0){
            return result1;
        }
        String fresult = result1+"+"+result2;
        return fresult;
    }
    public void addTransform(String function, String result) {
        transforms.put(function, result);
    }
    public void listTransforms() {
        for (Map.Entry<String, String> entry : transforms.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
    
    public static void main(String[] args){
        Laplace x = new Laplace();
        Scanner sc = new Scanner(System.in);
        String z = sc.nextLine();
        System.out.println(x.gettranform(z));
    }
}

