import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Cmdemailclient {
public static void main(String[] args) {


readinfiledate();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter option type: \n"
                + "1 - Adding a new recipient\n"
                + "2 - Sending an email\n"
                + "3 - Printing out all the recipients who have birthdays\n"
                + "4 - Printing out details of all the emails sent\n"
                + "5 - Printing out the number of recipient objects in the application");

        int option1 = scanner.nextInt();

        switch(option1){
            case 1:
                System.out.println("You want to add \n 1.Official  \n 2.Office Friends \n 3.Personal");
                int option2 = scanner.nextInt();
                if(option2==1) {
                    System.out.print( "Enter the name,email ID ,Designation \n Official:" );
                    System.out.println("name");
                    String name = scanner.next();
                    System.out.println("emailID");
                    String emailID =scanner.next();
                    System.out.println("Designation");
                    String Designation =scanner.next();
                    writeinFile(name,emailID,Designation );
                    Receiptant one = new Receiptant( name,emailID,Designation );

                   // Receiptantlist.add( one );

                   // System.out.println( input );
                }else if(option2==2){
                    System.out.print( "Enter the name,email ID ,Designation,DOB \n Office Friends: " );
                    System.out.println("name");
                    String Oname = scanner.next();
                    System.out.println("emailID");
                    String OemailID =scanner.next();
                    System.out.println("Designation");
                    String ODesignation =scanner.next();
                    System.out.println("DOB dd-MM-yyyy");
                    String date =scanner.next();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date2=null;
                    try {
                        //Parsing the String
                        date2 = dateFormat.parse(date);
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }

                    writeinFile( Oname,OemailID,ODesignation,date2 );

                }else {
                    System.out.print( "Enter the name,nick name,email ID ,DOB \n Personal " );
                    System.out.println( "name" );
                    String Oname = scanner.next();
                    System.out.println( "nick name" );
                    String Nname = scanner.next();

                    System.out.println( "DOB dd-MM-yyyy" );
                    String date = scanner.next();
                    SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MM-yyyy" );
                    Date date2 = null;
                    try {
                        //Parsing the String
                        date2 = dateFormat.parse( date );
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println( "emailID" );
                    String OemailID = scanner.next();
                    writeinFile( Oname,OemailID , date2 ,Nname );
                }
                // input format - Official: nimal,nimal@gmail.com,ceo
                // Use a single input to get all the details of a recipient
                // code to add a new recipient
                // store details in clientList.txt file
                // Hint: use methods for reading and writing files
                break;
            case 2:
                System.out.println("Enter the mailID");
                String email = scanner.next();
                System.out.println("Enter the Subject");
                String subject = scanner.next();
                System.out.println("Enter the Content");
                String content = scanner.next();

                sendmail( email,subject,content );
                // input format - email, subject, content
                // code to send an email
                break;
            case 3:
                System.out.println("DOB dd-MM-yyyy");
                String date1 =scanner.next();
                SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                Date date3=null;
                try {
                    //Parsing the String
                    date3 = dateFormat1.parse(date1);
                    //readinfiledate(date3);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print recipients who have birthdays on the given date
                break;
            case 4:
                // input format - yyyy/MM/dd (ex: 2018/09/17)
                // code to print the details of all the emails sent on the input date
                break;
            case 5:
                // code to print the number of recipient objects in the application
                break;

        }

        // start email client
        // code to create objects for each recipient in clientList.txt
        // use necessary variables, methods and classes

    }

    //method to write in textfile in the object serializing method
    public static void writeinFile(String name,String mail,String designation){
        Receiptant one = new Receiptant( name,mail,designation );
        try {
            ObjectOutputStream os = new ObjectOutputStream( new FileOutputStream( "clientList.txt",true ) );
            os.writeObject(one );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void writeinFile(String name,String mail,String designation,Date dob){
        Receiptant one = new Receiptant( name,mail,designation,dob );
        try {
            ObjectOutputStream os = new ObjectOutputStream( new FileOutputStream( "clientList.txt",true ) );
            os.writeObject(one);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void writeinFile(String name,String mail,Date dob,String nickname){
        Receiptant one = new Receiptant( name,mail,dob,nickname );
        try {
            ObjectOutputStream os = new ObjectOutputStream( new FileOutputStream( "clientList.txt",true) );
            os.writeObject(one);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //method to read the details from the text file
    public static void readinfiledate(){

        //deserialize the quarks.ser file
        try(
                InputStream file = new FileInputStream("clientList.txt");
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream (buffer);
        ){
             Receiptant[] receipt = new Receiptant[5];
             int i=0;
             while (i<5){
                 i=i+1;
                 receipt[i]= (Receiptant) input.readObject();

                 System.out.println(receipt[i].Designation);
             }
            }

        catch(Exception ex){
            System.out.println(ex);
        }

    }

    //method to send text mail
    public static void sendmail(String mail,String sub ,String content){

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.setProperty( "mail.smtp.ssl.enable", "true" );
        props.put( "mail.smtp.auth", "true" );
        props.put( "mail.smtp.starttls.enable", "true" );
        props.put( "mail.smtp.host", host );
        props.put( "mail.smtp.port", "465" );

        // Get the Session object.
        Session session = Session.getInstance( props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication(){

                        return new PasswordAuthentication( "Assignmentemail1997@gmail.com", "lavan1203" );
                    }
                } );


        // Create a default MimeMessage object.
        Message message = new MimeMessage( session );

        // Set From: header field of the header.
        try {
            message.setFrom( new InternetAddress( "ssignmentemail1997@gmail.com" ) );
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Set To: header field of the header.
        try {
            message.setRecipients( Message.RecipientType.TO, InternetAddress.parse( mail) );
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Set Subject: header field
        try {
            message.setSubject( sub );
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Now set the actual message
        try {
            message.setText( content );
            //System.out.println("working");
        } catch (javax.mail.MessagingException e1) {
            e1.printStackTrace();
        }

        // Send message
        try {
            Transport.send( message );
            System.out.println( "Sent message successfully...." );

        }catch (Exception e) {
            System.out.println(e);
           // alert( "Email is not valid","You have Entered an invalid mail id modify it " );
            // e.printStackTrace();
        }


    }



    private static void elseif1(boolean b) {
    }
}

// create more classes needed for the implementation (remove the  public access modifier from classes when you submit your code)

