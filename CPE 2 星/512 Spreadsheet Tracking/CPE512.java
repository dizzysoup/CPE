import java.util.* ;

import javax.print.DocFlavor.STRING;


public class CPE512 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int index = 0 ;
        while(sc.hasNextInt()){
            index ++ ;
            int r = sc.nextInt();
            int c = sc.nextInt();
            if(r==0 && c==0)break ;
            Vector<Vector<String>> vec = new Vector<Vector<String>>();
            
            for(int i=1;i <= r ; i++){
                Vector<String> vecr = new Vector<String>();
                for(int j = 1; j <= c ; j++){
                    vecr.add(i+"-" + j);
                }
                vec.add(vecr);
            }

           

            int op = sc.nextInt();
            
            while(op-- > 0){
                String command = sc.next();
                switch(command){
                    case "DR" : 
                        DR(sc,vec);
                        break ;
                    case "DC":
                        DC(sc,vec);
                        break;
                    case "IC" :
                        IC(sc,vec);
                        break ;
                    case "IR" :
                        IR(sc,vec);
                        break;
                    case "EX":
                        EX(sc,vec);
                        break;
                }
            }
            
            int query = sc.nextInt();
            Vector<String> output = new Vector<String>();
            while(query -- > 0){
                int r1 = sc.nextInt();
                int c1 = sc.nextInt();
                int r2 = 0;
                int c2 = 0;
                String str = r1 + "-" + c1 ;
                Boolean chk = false ;
                for(int i = 0; i < vec.size();i++){
                    for(int j=0;j<vec.get(i).size();j++){
                        if(vec.get(i).get(j).equals(str)){
                            r2 = i+1;
                            c2 = j+1;
                            chk = true;
                            break ;
                        }
                    }
                    if(chk)break;
                }
                String result = "Cell data in (" + r1 + "," + c1 + ")" ; 
                if(chk)
                    result += " move to (" + r2 + "," + c2 + ")";
                else 
                    result += " GONE";
                output.add(result);
            }
            System.out.println("Spreadsheet #" + (index));

            for(String str : output)
                System.out.println(str);
            
        }

        sc.close();
    }

  
    public static void DR(Scanner sc , Vector<Vector<String>> vec){
        int A = sc.nextInt();
        while(A-- > 0){
            int num = sc.nextInt();
            for(int i=0;i<vec.size();i++){
                String[] vect = vec.get(i).get(0).split("-");
                if(Integer.parseInt(vect[0]) == num){
                    vec.remove(i);
                }
            }
        }   
    }

    public static void DC(Scanner sc , Vector<Vector<String>> vec){
        int A = sc.nextInt();
        while(A-- > 0){
            int num = sc.nextInt();
            for(int i=0;i<vec.get(0).size();i++){
                String[] vect = vec.get(0).get(i).split("-");
                if(Integer.parseInt(vect[1]) == num){
                    for(int j=0;j<vec.size();j++)
                        vec.get(j).remove(i);
                    break;
                }

            }
        }
    }

    public static void IC(Scanner sc , Vector<Vector<String>> vec){
        int A = sc.nextInt();
        String[] targetcol = new String[A];
        //先記錄原本的數值
        for(int i =0;i< targetcol.length;i++){
            int num = sc.nextInt();
            targetcol[i] = vec.get(0).get(num-1);
        }
        for(int i=0;i<targetcol.length;i++){
            for(int j=0;j<vec.get(0).size();j++){
                if(vec.get(0).get(j) == targetcol[i]){
                    for(int k=0;k<vec.size();k++){
                        vec.get(k).add(j,"0-0");
                    }
                    break;
                }
            }
        }
    }

    public static void IR(Scanner sc , Vector<Vector<String>> vec){
        int A = sc.nextInt();
        String[] targetrow = new String[A];
        Vector<String> vect = new Vector<String>();
        for(int i = 0 ; i < vec.get(0).size();i++)
            vect.add("0-0");
        //先記錄原本數值
        for(int i=0;i<targetrow.length;i++){
            int num = sc.nextInt();
            targetrow[i] = vec.get(num-1).get(0);
        }
        
        for(int i =0;i < targetrow.length;i++){
            for(int j = 0 ; j < vec.size();j++){
                if(vec.get(j).get(0).equals(targetrow[i])){
                    vec.add(j,vect);
                    break;
                }
            }
        }
    }

    public static void EX(Scanner sc , Vector<Vector<String>> vec){
        int r1 = sc.nextInt()-1;
        int c1 = sc.nextInt()-1;
        int r2 = sc.nextInt()-1;
        int c2 = sc.nextInt()-1;

        String str1 = vec.get(r1).get(c1);
        String str2 = vec.get(r2).get(c2);
        vec.get(r1).set(c1,str2);
        vec.get(r2).set(c2,str1);
    }

}