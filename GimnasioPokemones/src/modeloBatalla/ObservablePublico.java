package modeloBatalla;
import java.util.Observable;

public class ObservablePublico extends Observable {
    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }
}