package IO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.LinkedHashMap;
import java.util.List;

public class MyCompressorOutputStream extends OutputStream {

    private ObjectOutputStream out;

    public MyCompressorOutputStream(OutputStream out){
        try {
            this.out = new ObjectOutputStream(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(int b){
        try {
            out.writeObject(b);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(byte[] b){
        List ans=new ArrayList();
        byte[] finalAns;
        int i=0,j=0;
        while(i<6){
            if(b[j]==-128){
                i++;
            }
            ans.add((byte)b[j]);
            j++;
        }
        while(j<b.length){
            int sum=0;
            i=0;
            while(j<b.length && i<8){
                sum=sum+(int)Math.pow(2,i)*b[j];
                i++;
                j++;
            }
            if(sum>127)
                ans.add((byte)(127-sum));
            else ans.add((byte)sum);
        }
        finalAns=new byte[ans.size()];
        for(int k=0;k<ans.size();k++)
            finalAns[k]=(byte)(ans.get(k));
        try {
            out.writeObject(finalAns);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
