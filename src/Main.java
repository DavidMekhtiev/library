import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.menu();
    }
}

class Book{
    private String title;
    private int isbn;
    private String genre;
    private String author;
    private int year;
    private int quantity;

    public Book(String title, int isbn, String genre, String author, int year, int quantity) {
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.author = author;
        this.year = year;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getTitle() {
        return title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void printBook(){
        System.out.println("ISBN: " + this.isbn);
        System.out.println("��������: " + this.title);
        System.out.println("����: " + this.genre);
        System.out.println("�����: " + this.author);
        System.out.println("���: " + this.year);
        System.out.println("����������: " + this.quantity);
    }
}

class User{
    private int id;
    private String name;
    private String group;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public User(int id, String name, String group) {
        this.id = id;
        this.name = name;
        this.group = group;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
    public String getName() {
        return name;
    }
    public void printUser(){
        System.out.println("TEST");
        System.out.println("ID: " + this.id);
        System.out.println("���: " + this.name);
        System.out.println("������: " + this.group);
        System.out.print("�����: ");
        for (Book borrowedBook : borrowedBooks) {
            System.out.println(borrowedBook.getTitle());
        }
    }

    public int getId() {
        return id;
    }
}

class Library{
    private Scanner in = new Scanner(System.in);
    ArrayList<User> users;
    ArrayList<Book> books;

    public Library() {
        users = new ArrayList<>();
        books = new ArrayList<>();
    }

    public void usersMenu(){
        System.out.println();
        for(int i = 0; i < users.size(); i++) {
            System.out.println(i+1 + "." + users.get(i).getName());
        }
        System.out.println("0.��������� � ����");
        int n = in.nextInt() -1 ;
        if(n+1 == 0|| n >= users.size() || n < 0){
            menu();
        } else {
            users.get(n).printUser();
            menu();
        }
    }
    public void booksMenu(){
        System.out.println();
        for(int i = 0; i < books.size(); i++) {
            System.out.println(i+1 + "." + books.get(i).getTitle());
        }
        System.out.println("0.��������� � ����");
        int n = in.nextInt()-1;
        if(n+1 == 0 || n >= users.size() || n < 0){
            menu();
        } else {
            books.get(n).printBook();
            menu();
        }
    }
    public void addBookToUser(){
        Book bookie;
        System.out.println();
        System.out.println("�������� �����: ");
        for(int i = 0; i < books.size(); i++) {
            System.out.println(i+1 + "." + books.get(i).getTitle());
        }
        System.out.println("0.��������� � ����");
        int m = in.nextInt()-1;
        if(m+1 == 0 || m > books.size() || m < 0){
            menu();
        } else {
            bookie = books.get(m);
            System.out.println("�������� ������������: ");
            for(int i = 0; i < users.size(); i++) {
                System.out.println(i+1 + "." + users.get(i).getName());
            }
            System.out.println("0.��������� � ����");
            int n = in.nextInt()-1;
            if(n+1 == 0 || n >= users.size() || n < 0){
                menu();
            } else {
                User us = users.get(n);
                books.get(m).setQuantity(bookie.getQuantity()-1);
                if(books.get(m).getQuantity() == 0){
                    books.remove(m);
                }
                ArrayList<Book> arr = us.getBorrowedBooks();
                arr.add(bookie);
                us.setBorrowedBooks(arr);
                menu();
            }
        }
    }
    public void returnBookFromUser(){
        User us;
        System.out.println();
        System.out.println("�������� ������������: ");
        for(int i = 0; i < users.size(); i++) {
            System.out.println(i+1 + "." + users.get(i).getName());
        }
        System.out.println("0.��������� � ����");
        int m = in.nextInt()-1;
        if(m+1 == 0 || m >= users.size() || m < 0){
            menu();
        } else {
            us = users.get(m);
            System.out.println("�������� �����: ");
            for(int i = 0; i < us.getBorrowedBooks().size(); i++) {
                System.out.println(i+1 + "." + us.getBorrowedBooks().get(i).getTitle());
            }
            System.out.println("0.��������� � ����");
            int n = in.nextInt()-1;
            if(n+1 == 0 || n >= us.getBorrowedBooks().size() || n < 0){
                menu();
            } else {
                Book bookie = us.getBorrowedBooks().get(n);
                ArrayList<Book> arr = us.getBorrowedBooks();
                arr.remove(bookie);
                us.setBorrowedBooks(arr);
                if(!books.contains(bookie)){
                    books.add(bookie);
                }else{
                    books.get(books.indexOf(bookie)).setQuantity(books.get(books.indexOf(bookie)).getQuantity()+1);
                }
                menu();
            }
        }
    }
    public void addUser(){
        System.out.println();
        System.out.print("���: ");
        in.nextLine();
        String name = in.nextLine();
        System.out.print("������: ");
        String group = in.nextLine();
        int id = 0;
        if(users.size() - 1 > -1){
            id = users.get(users.size() - 1).getId();
        }
        User us = new User(id,name,group);
        users.add(us);
        menu();
    }

    public void addBook(){
        System.out.println();
        System.out.print("��������: ");
        in.nextLine();
        String title = in.nextLine();
        System.out.print("����: ");
        String genre = in.nextLine();
        System.out.print("�����: ");
        String author = in.nextLine();
        System.out.print("ISBN: ");
        int isbn = in.nextInt();
        System.out.print("���: ");
        int year = in.nextInt();
        System.out.print("����������: ");
        int quantity = in.nextInt();
        Book bk = new Book(title,isbn,genre,author,year,quantity);
        books.add(bk);
        menu();
    }
    public void menu(){
        System.out.println();
        System.out.println("1.�������� ��� �����\n2.�������� ���� �������������\n3.���� ������������ �����\n4.������� �����\n5.�������� ������������\n6.�������� �����\n0.�����");
        int n = in.nextInt();
        switch (n){
            case 1:
                booksMenu();
            case 2:
                usersMenu();
            case 3:
                addBookToUser();
            case 4:
                returnBookFromUser();
            case 5:
                addUser();
            case 6:
                addBook();
            case 0:
                System.exit(0);
            default:
                System.exit(0);
        }
    }

}