package LongIntPack;

/**
 * Madhumita Ghude
 * Course: CS 610852
 * Programming Assignment # 2
 * Test Cases
 */
public class TestLongInt {
    public static void main(String[] args){

        LongInt A = new LongInt(1, new int[]{11568359, 34529077, 5459, 10056810, 00000000, 142355});
        LongInt B = new LongInt(-1, new int[]{54317890, 45948474, 35000327});
        LongInt C =
            new LongInt(1, "293023902347029734029734209374209734209374209372348723498729348728934728937492");
        LongInt D =
            new LongInt(-1, "98534342983742987342987339234098230498203894209928374662342342342356723423423");
        LongInt E =
            new LongInt( -1, "84364131684386183513516846948354348943643518468434351684843516846843153846843"
            +"13846813153843135138413513843813513813138438435153454154515151513141592654543515316848613242587561516511"
            +"233246174561276521672162416274123076527612");

        LongInt[] alpha = {A, B, C, D, E};
        String[] names = {"A", "B", "C", "D", "E"};

        System.out.println("\n1. Initialize Long Integers A-E.\n");
        for(int i=0; i<alpha.length; i++){
            System.out.print(names[i]+" : ");
            alpha[i].printArray();
        }

        System.out.println("\n2. Print each Long Integer to standard output using print().\n");
        for(int i=0; i<alpha.length; i++){
            System.out.print(names[i]+" = ");
            alpha[i].print();
        }

        System.out.println("\n3. For each Long Integer compare it to A-E using equalTo, lessThan, greaterThan.\n");
        for(int i=0; i<alpha.length; i++){
            for(int j=0; j<alpha.length; j++){
                System.out.println(names[i]+" = "+names[j]+" : "+alpha[i].equalTo(alpha[j]));
                System.out.println(names[i]+" < "+names[j]+" : "+alpha[i].lessThan(alpha[j]));
                System.out.println(names[i]+" > "+names[j]+" : "+alpha[i].greaterThan(alpha[j]));
                System.out.println("---");
            }
        }

        System.out.println("\n4. For each LongInt add it to every other LongInt (one at a time) and print the result.\n");
        for(int i=0; i<alpha.length; i++){
            for(int j=0; j<alpha.length; j++){
                System.out.print(names[i]+" + "+names[j]+" = ");
                alpha[i].add(alpha[j]).print();
            }
            System.out.println("------");
        }

        System.out.println("\n5. For each LongInt subtract it from every other LongInt (one at a time) and print the result.\n");
        for(int i=0; i<alpha.length; i++){
            for(int j=0; j<alpha.length; j++){
                System.out.print(names[i]+" - "+names[j]+" = ");
                alpha[i].subtract(alpha[j]).print();
            }
            System.out.println("------");
        }
    }
}
