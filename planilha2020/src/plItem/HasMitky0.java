package plItem;

//import com.itextpdf.text.pdf.PdfContentByte;
import cores.Cor;
import java.awt.Color;
import java.awt.Graphics;
import planilha.MPannel;

/**
 *
 * @author Samsung
 */
public interface HasMitky0 extends IsDItem{

    public void unchMitky();

    public void reSize(int dx, int i, int i0, int i1);

    public void draw(Graphics g);

    public String toFile();

//    public void toDoc(PdfContentByte cb, MPannel mPannel);
//
//    public void drawDoc(PdfContentByte cb, MPannel mPannel);

    public int getFSize();

    public void setCor(Color textCol, Color backCor);

    /**
     *
     * @return
     */
    public String writeArrows();

    @Override
    public int getId();

    @Override
    public boolean hasId(int fndId);

}
