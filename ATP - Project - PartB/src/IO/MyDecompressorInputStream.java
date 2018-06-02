package IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MyDecompressorInputStream extends InputStream{
    private ObjectInputStream in;

    public MyDecompressorInputStream(InputStream in)throws IOException {
        this.in = new ObjectInputStream(in);
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
    private int getV(byte num){
        if(num>=0)
            return num;
        else return 127-num;
    }
    public int read(byte[] to) throws IOException {
        byte[] b;
        try {
            b=(byte[])in.readObject();
            List ans=new ArrayList();
            byte[] finalAns;
            int counter=0,i=0,rowsNum=0,colNum=0;
            while(i<b.length && counter<6){
                if(b[i]==-128){
                    counter++;
                    ans.add((byte)(-128));
                }
                else {
                    if (counter == 4)
                        rowsNum = rowsNum + getV(b[i]);
                    if (counter == 5)
                        colNum = colNum + getV(b[i]);
                    ans.add((byte)b[i]);
                }
                i++;
            }
            int arrSize=rowsNum*colNum;
            while(i<b.length){
                int temp=getV(b[i]);
                int cnt=Math.min(8,arrSize);
                while(cnt>0 && arrSize>0){
                    ans.add((byte)(temp%2));
                    temp=temp/2;
                    cnt--;
                }
                arrSize-=Math.min(8,arrSize);
                i++;
            }
            finalAns=new byte[ans.size()];
            for (int j=0;j<ans.size();j++)
                to[j]=(byte)(ans.get(j));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
