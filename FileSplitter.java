import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FileSplitter {
    
    //membaca isi dari file teks
    public static String readFile(String fileName) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        // Membaca setiap baris dari file dan menambahkannya ke variabel content
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close(); // Menutup file setelah selesai dibaca
        return content.toString(); // Mengembalikan isi file sebagai String
    }

    //memotong isi file menjadi beberapa bagian dan menyimpannya ke dalam Queue
    public static Queue<String> splitFile(String content, int partSize) {
        Queue<String> queue = new LinkedList<>(); // Inisialisasi Queue untuk menyimpan potongan file
        int length = content.length(); // Mendapatkan panjang isi file
        int start = 0;
        
        //memotong file berdasarkan ukuran bagian yang diberikan
        while (start < length) {
            int end = Math.min(start + partSize, length); //menentukan batas akhir dari setiap potongan
            queue.add(content.substring(start, end)); //menambahkan potongan teks ke dalam Queue
            start = end; //memperbarui posisi awal untuk potongan berikutnya
        }
        
        return queue; //mengembalikan Queue yang berisi potongan file
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            //langkah 1: Membaca file dari path yang dimasukkan oleh pengguna
            System.out.print("Masukkan path file teks: ");
            String filePath = scanner.nextLine();
            String content = readFile(filePath); // Membaca isi file
            
            //langkah 2: Mendapatkan ukuran tiap potongan dari input pengguna
            System.out.print("Masukkan ukuran tiap potongan teks: ");
            int partSize = scanner.nextInt();
            
            //langkah 3: Memotong isi file berdasarkan ukuran bagian yang diinginkan
            Queue<String> fileParts = splitFile(content, partSize);
            
            //langkah 4: Mencetak setiap potongan dari Queue
            int partNumber = 1;
            while (!fileParts.isEmpty()) {
                System.out.println("Bagian " + partNumber + ":"); //mencetak nomor bagian
                System.out.println(fileParts.poll()); //mengambil dan mencetak bagian dari Queue
                partNumber++; 
             }
            
        } catch (IOException e) {
            //jika ada kesalahan saat membaca file, tampilkan pesan error
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
