class Passenger {
  String name;  
  Passenger next;  // Referensi ke penumpang berikutnya dalam daftar

  public Passenger(String name) {
      this.name = name;
      this.next = null;
  }
}

class Flight {
  private Passenger head;  // Referensi ke penumpang pertama dalam daftar

  public Flight() {
      this.head = null;
  }

  //menambahkan penumpang baru di akhir daftar
  public void addPassenger(String name) {
      Passenger newPassenger = new Passenger(name);
      
      //jika daftar penumpang kosong, set penumpang baru sebagai penumpang pertama
      if (head == null) {
          head = newPassenger;
      } else {
          Passenger current = head;
          while (current.next != null) {
              current = current.next;
          }
          
          //menambahkan penumpang baru di akhir daftar
          current.next = newPassenger;
      }
  }

  //menghapus penumpang berdasarkan nama
  public void removePassenger(String name) {
      //jika daftar kosong
      if (head == null) {
          System.out.println("Daftar penumpang kosong.");
          return;
      }

      //jika penumpang yang ingin dihapus adalah penumpang pertama
      if (head.name.equals(name)) {
          head = head.next;  // Menghapus penumpang pertama
          System.out.println(name + " telah dihapus dari daftar.");
          return;
      }

      Passenger current = head;

      //menemukan penumpang yang akan dihapus
      while (current.next != null && !current.next.name.equals(name)) {
          current = current.next;
      }

      // Jika penumpang ditemukan, hapus penumpang
      if (current.next != null) {
          current.next = current.next.next;
          System.out.println(name + " telah dihapus dari daftar.");
      } else {
          // Jika penumpang tidak ditemukan
          System.out.println(name + " tidak ditemukan dalam daftar.");
      }
  }

  //menampilkan semua penumpang dalam daftar
  public void displayPassengers() {
      if (head == null) {
          System.out.println("Daftar penumpang kosong.");
          return;
      }

      Passenger current = head;
      
      //mencetak nama penumpang
      while (current != null) {
          System.out.println(current.name);
          current = current.next;
      }
  }

  public static void main(String[] args) {
      Flight flight = new Flight();
      
      //menambahkan penumpang ke dalam daftar penerbangan
      flight.addPassenger("Irfan Arifin");
      flight.addPassenger("Nanda Kurniawan");
      flight.addPassenger("M. Fadhil");
      
      // Menampilkan daftar penumpang
      System.out.println("Daftar Penumpang:");
      flight.displayPassengers();

      // Menghapus penumpang
      System.out.println("\nMenghapus penumpang 'Irfan Arifin':");
      flight.removePassenger("Irfan Arifin");

      // Menampilkan daftar penumpang setelah penghapusan
      System.out.println("\nDaftar Penumpang Setelah Penghapusan:");
      flight.displayPassengers();
  }
}

