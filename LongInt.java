package LongIntPack;

/**
 * Madhumita Ghude
 * Course: CS 610852
 * Programming Assignment # 2
 * No special instructions or problems
 * Test case input(s) in TestLongInt.java
 */
public class LongInt {
    private final int[] array;
    private final int sign;

    /**
     * Initializes a LongInt via an array of integers and an integer containing 1 or -1, indicating the sign of the overall LongInteger
     */
    public LongInt(int sign, int[] array) {
        this.sign = sign;
        this.array = array;
    }

    /**
     * Initializes a LongInt via a string of integers and a sign
     */
    public LongInt(int sign, String num){
        this.sign=sign;
        if(num.length()>(num.length()/8)*8){
            array = new int[(num.length()/8)+1];
        }else {
            array = new int[num.length()/8];
        }
        if(num.length()>=8) {
            int j=0;
            int k=num.length()-8;
            while(num.length()>0){
                if(k>0) {
                    array[j] = new Integer(num.substring(k));
                    num = num.substring(0, k);
                }else {
                    array[j] = new Integer(num);
                    num = "";
                }
                j++;
                k=k-8;
            }
        }else{
            array[0] = new Integer(num);
        }
    }

    /**
     * Prints the String representation of the LongInt to standard output
     */
    public void print() {
        if(sign==-1)    System.out.print("-");
        System.out.print(array[array.length-1]);    //in case first element has <8 digits to avoid leading zeros
        for(int i=array.length-2; i>=0; i--){
            //if <8 digits pad with zeros
            System.out.print(String.format("%08d", array[i]));
        }
        System.out.println();
    }

    /**
     * Determines if the LongInt is equal to the LongInt other
     */
    public boolean equalTo(LongInt other) {
        if(sign!=other.sign || array.length!=other.array.length){
            return false;
        }else{
            for(int i=0; i<array.length; i++){
                if(array[i]!=other.array[i]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Determines if the LongInt is less than the LongInt other
     */
    public boolean lessThan(LongInt other) {
        if(equalTo(other))  return false;
        //if this is negative
        else if(sign<0){
            //if other is positive this is less
            if(other.sign>0)    return true;

            //if both are negative, the one farther from 0 is less
            if(array.length>other.array.length){
                return true;
            }else if(array.length<other.array.length) {
                return false;
            }else{
                for(int i=array.length-1; i>=0; i--){
                    if(array[i]<other.array[i]){
                        return false;
                    }
                }
            }
            return true;
        }
        //if this is positive
        else{
            //if other is negative this is greater
            if(other.sign<0)    return false;

            //if both are positive, the one closer to 0 is less
            if(array.length<other.array.length){
                return true;
            }else if(array.length>other.array.length){
                return false;
            }else{
                for(int i=array.length-1; i>=0; i--){
                    if(array[i]>other.array[i]){
                        return false;
                    }
                }
            }
            return true;
        }
    }

    /**
     * Determines if the LongInt is greater than the LongInt other
     */
    public boolean greaterThan(LongInt other) {
        //return false if this is less than or equal to other, true otherwise
        return !(lessThan(other) || equals(other));
    }

    /**
     * Adds the LongInt and returns the result as a new LongInt. Ex. this=2, other=1
     */
    public LongInt add(LongInt other){

        //if both are positive or both are negative (2+1=3 or (-2)+(-1)=-3)
        if(sign==other.sign){
            int[] newArray = new int[Math.max(array.length, other.array.length)];
            int[] addArray = new int[Math.min(array.length, other.array.length)];

            //copy larger array to newArray and smaller array to addArray
            if(array.length>other.array.length) {
                System.arraycopy(array, 0, newArray, 0, array.length);
                System.arraycopy(other.array, 0, addArray, 0, other.array.length);
            }else {
                System.arraycopy(other.array, 0, newArray, 0, other.array.length);
                System.arraycopy(array, 0, addArray, 0, array.length);
            }

            //add all elements of newArray and addArray together
            for(int i=0; i<addArray.length; i++){
                //sum is 8 digits or less (no carry forward)
                if(newArray[i]+addArray[i]<=99999999){
                    newArray[i]=newArray[i]+addArray[i];
                }
                //carry forward the 1
                else{
                    newArray[i]=newArray[i]+addArray[i]-100000000;
                    //if last element in newArray becomes >99999999 add one index to newArray
                    if(i==newArray.length-1) {
                        int[] temp = newArray;
                        newArray = new int[newArray.length + 1];
                        System.arraycopy(temp, 0, newArray, 0, temp.length);
                    }
                    newArray[i+1]=newArray[i+1]+1;
                }
            }
            return new LongInt(sign, newArray);
        }
        //this is positive and other is negative (2+(-1)=2-1=1)
        else if(sign>0 && other.sign<0){
            LongInt li = new LongInt(1, other.array);
            return subtract(li);
        }
        //this is negative and other is positive (-2+1=1-2=-(2-1)=-1)
        else{
            LongInt li=new LongInt(1, array);
            return other.subtract(li);
        }
    }

    /**
     * Subtracts the LongInt and returns the result as a new LongInt Ex. this=2, other=1
     */
    public LongInt subtract(LongInt other){
        //if this equals other (1-1=0)
        if(equals(other))
            return new LongInt(1, new int[]{0});

        int[] newArray = new int[Math.max(array.length, other.array.length)];
        LongInt li;
        //if both are positive (2-1)
        if(sign>0 && other.sign>0){
            //this is greater than other (2-1=1)
            if(!lessThan(other)){
                for(int i=0; i<other.array.length; i++){
                    if(newArray[i]+array[i]-other.array[i]<0){
                        newArray[i] = newArray[i]+array[i]+100000000-other.array[i];
                        newArray[i+1] = newArray[i+1]-1;
                    }else{
                        newArray[i] = newArray[i]+array[i]-other.array[i];
                    }
                }
                for(int j=other.array.length; j<array.length; j++){
                    newArray[j] = newArray[j]+array[j];
                }

                //remove leading zeroes if necessary
                if(newArray[newArray.length-1]==0) {
                    int k = newArray.length-1;
                    while(newArray[k]==0){
                        k--;
                    }
                    //k=index of last nonzero number in array, so first number in LongInt value
                    int[] temp = new int[k+1];
                    System.arraycopy(newArray, 0, temp, 0, temp.length);
                    newArray = temp;
                }
                return new LongInt(1, newArray);
            }
            //this is less than other (this=1, other=2; 1-2=-1)
            else{
                li = other.subtract(this);
                return new LongInt(-1, li.array);
            }
        }
        //this is positive and other is negative (2-(-1)=2+1=3)
        else if(sign>0 && other.sign<0){
            li = new LongInt(1, other.array);
            return add(li);
        }
        //this is negative and other is positive (-2-1=-2+(-1)=-3)
        else if(sign<0 && other.sign>0){
            li = new LongInt(-1, other.array);
            return add(li);
        }
        //both are negative (-2-(-1)=-2+1=1-2=-(2-1)=-1)
        else{
            li = new LongInt(1, other.array);
            return add(li);
        }
    }

    /**
     * Prints sign and array to display LongInt object values
     */
    public void printArray(){
        String test = "";
        if(sign<0)  test+="sign =-1, array = { ";
        else    test+="sign = 1, array = { ";
        for(int i: array){
            //test+=String.format("%08d", i)+" ";
            test+=i+" ";
        }
        test+="}";
        System.out.println(test);
    }
}