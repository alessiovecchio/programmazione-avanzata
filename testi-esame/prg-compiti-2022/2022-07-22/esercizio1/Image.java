package esercizio1;

public class Image {

  public byte[][] red;
  public byte[][] green;
  public byte[][] blue;

  public Image(int r, int c) {
    red = new byte[r][c];
    green = new byte[r][c];
    blue = new byte[r][c];
  }

  public int getRows() {
    return red.length;
  }

  public int getColumns() {
    return red[0].length;
  }

  public String toString(){
    String s = "";
    for(int i=0; i<red.length; i++){
      for(int j=0; j<red[0].length; j++){
        s += red[i][j] + " ";
      }
      s += "\n";
    }
    for(int i=0; i<green.length; i++){
      for(int j=0; j<green[0].length; j++){
        s += green[i][j] + " ";
      }
      s += "\n";
    }
    for(int i=0; i<blue.length; i++){
      for(int j=0; j<blue[0].length; j++){
        s += blue[i][j] + " ";
      }
      s += "\n";
    }
    return s;
  }
}
