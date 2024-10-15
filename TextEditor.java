import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;

public class TextEditor {
    private Stack<String> undoStack;    //menyimpan status sebelum perubahan (untuk undo)
    private Stack<String> redoStack;    //menyimpan status untuk memulihkan perubahan (untuk redo)
    private Queue<String> text;         //menyimpan teks yang saat ini sedang ada di editor

    public TextEditor() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        text = new LinkedList<>();
    }

    //menambahkan teks ke text editor
    public void write(String newText) {
        //push state sekarang ke undoStack sebelum perubahan dilakukan
        saveStateForUndo();

        //kosongkan redoStack karena state baru sudah ditambahkan
        redoStack.clear();

        //tambahkan teks baru ke editor
        text.add(newText);
    }

    //menampilkan semua teks yang telah ditulis ke dalam editor
    public void show() {
        if (text.isEmpty()) {
            System.out.println("(Editor Kosong)");
        } else {
            System.out.println("Isi Text Editor:");
            for (String line : text) {
                System.out.println(line);
            }
        }
    }

    //melakukan undo (mengembalikan ke status sebelumnya)
    public void undo() {
        if (!undoStack.isEmpty()) {
            //simpan state sekarang ke redoStack sebelum perubahan
            saveStateForRedo();

            //ambil state sebelumnya dari undoStack dan update isi editor
            String previousState = undoStack.pop();
            updateEditorWithState(previousState);
        } else {
            System.out.println("Tidak ada aksi untuk di-undo.");
        }
    }

    //melakukan redo (memulihkan ke status terbaru setelah undo)
    public void redo() {
        if (!redoStack.isEmpty()) {
            //simpan state sekarang ke undoStack sebelum perubahan
            saveStateForUndo();

            //ambil state dari redoStack dan update isi editor
            String redoState = redoStack.pop();
            updateEditorWithState(redoState);
        } else {
            System.out.println("Tidak ada aksi untuk di-redo.");
        }
    }

    //menyimpan state text editor ke dalam undo stack
    private void saveStateForUndo() {
        undoStack.push(getCurrentState());
    }

    //menyimpan state text editor ke dalam redo stack
    private void saveStateForRedo() {
        redoStack.push(getCurrentState());
    }

    //mendapatkan state saat ini dalam bentuk string
    private String getCurrentState() {
        StringBuilder sb = new StringBuilder();
        for (String line : text) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    //mengupdate text editor berdasarkan state yang diberikan
    private void updateEditorWithState(String state) {
        text.clear();  // Kosongkan teks saat ini
        if (!state.isEmpty()) {
            String[] lines = state.split("\n");
            for (String line : lines) {
                text.add(line);
            }
        }
    }

    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        // Test
        editor.write("Hallo semua");
        editor.write("ini adalah text editor.");
        editor.show(); // Menampilkan teks

        System.out.println("\n-- Undo --");
        editor.undo(); // Undo
        editor.show(); // Menampilkan teks

        System.out.println("\n-- Redo --");
        editor.redo(); // Redo
        editor.show(); // Menampilkan teks

        System.out.println("\n-- Write more --");
        editor.write("Tambahkan text.");
        editor.show(); // Menampilkan teks

        System.out.println("\n-- Undo multiple --");
        editor.undo(); // Undo
        editor.undo(); // Undo
        editor.show(); // Menampilkan teks
    }
}
