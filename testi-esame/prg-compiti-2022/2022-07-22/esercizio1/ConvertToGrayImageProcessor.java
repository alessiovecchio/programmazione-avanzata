package esercizio1;

import java.io.*;

public class ConvertToGrayImageProcessor extends ImageProcessor {

  public ConvertToGrayImageProcessor(String f) throws FileNotFoundException {
    super(f);
  }

  protected void processRow(Image i, int j) {
    int c = i.getColumns();
    for(int x=0; x<c; x++) {
      int r = (i.red[j][x] + i.green[j][x] + i.blue[j][x]) / 3;
      i.red[j][x] = (byte)r;
      i.green[j][x] = (byte)r;
      i.blue[j][x] = (byte)r;
    }
  }
}
