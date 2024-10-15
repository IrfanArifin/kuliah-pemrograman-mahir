class Song {
  String title;  
  Song next;   

  public Song(String title) {
      this.title = title;
      this.next = null;
  }
}

class Playlist {
  private Song head;  
  public Playlist() {
      this.head = null;
  }

  //menambahkan lagu baru di akhir playlist
  public void addSong(String title) {
      Song newSong = new Song(title);
      
      //jika playlist kosong, set lagu baru sebagai lagu pertama
      if (head == null) {
          head = newSong;
      } else {
          Song current = head;
          
          while (current.next != null) {
              current = current.next;
          }
          
          //menambahkan lagu baru di akhir daftar
          current.next = newSong;
      }
  }

  //menampilkan semua lagu dalam playlist
  public void displayPlaylist() {
      if (head == null) {
          System.out.println("Playlist kosong.");
          return;
      }
      
      Song current = head;

      while (current != null) {
          System.out.println(current.title);
          current = current.next;
      }
  }

  public static void main(String[] args) {
      Playlist playlist = new Playlist();
      
      //menambahkan lagu ke playlist
      playlist.addSong("Apa Mungkin");
      playlist.addSong("Satu Bulan");
      playlist.addSong("Untungnya Hidup");
      
      //menampilkan playlist
      playlist.displayPlaylist();
  }
}

