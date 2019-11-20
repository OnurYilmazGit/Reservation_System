//ONUR YILMAZ S009604

import java.sql.*;
import java.util.Scanner;

public class Database {
    private static final String DB_URL = "jdbc:mysql://localhost/Homework1?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "bnm678hjk.";
    private static Connection conn = null;
    private static Statement stmt = null;
    private static PreparedStatement pstmt = null;
    private static int a = 1;

    public void init() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Problem!");
        }
    }

    private static void events(int i) throws SQLException {
        Scanner input = new Scanner(System.in);
        Statement stmt = conn.createStatement();

        if (i == 1) {
            System.out.print("Please enter Organizer Name and Surname : ");
            String name_of_org = input.nextLine().toLowerCase().trim();
            System.out.print("Please enter Organizer ID: ");
            int zero = input.nextInt();
            String sql = "INSERT INTO Organizer (`Organizer_ID`, `Organizer_N_and_S`) VALUES ('" + zero + "','" + name_of_org + "')";
            int number_row = stmt.executeUpdate(sql);
            if (number_row > 0) {

                System.out.println(number_row + " row(s) affected!");
                System.out.println("Organizer successfully added to Database");
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                String[] args = new String[0];
                main(args);
            } else
                events(12);
        } else if (i == 2) {
            System.out.print("--List of Users--\n");
            String search0 = "select Organizer_ID, Organizer_N_and_S from Organizer";
            ResultSet rh = stmt.executeQuery(search0);
            while (rh.next()) {
                String organizer_info = rh.getString("Organizer_ID");
                String organizer_info2 = rh.getString("Organizer_N_and_S");
                System.out.format("%s\t%s\n", "Organizer_ID: " + organizer_info, "Organizer Name and_Surname: " + organizer_info2);
            }
            System.out.print("----->>> PLEASE ENTER REMOVING ORGANIZER ID NUMBER: ");
            int zero = input.nextInt();
            String sql = "DELETE FROM Organizer WHERE (Organizer_ID = '" + zero + "')";
            int number_row = stmt.executeUpdate(sql);
            if (number_row == 0) {
                System.out.println("There is no such a entry in database. Please try again. ");
                while (a >= 0) {
                    System.out.println("You can type " + a + " times more");
                    a--;
                    events(i);
                }
            } else {
                System.out.println(number_row + " row(s) affected!");
                System.out.println("Organizer successfully deleted from Database");
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                String[] args = new String[0];
                main(args);
            } else
                events(12);
        } else if (i == 3) {
            System.out.println("--Add Location---");
            System.out.print("Please enter Location ID: ");
            String zero = input.nextLine().toLowerCase().trim();
            System.out.print("Please enter Location Name: ");
            String first = input.nextLine().trim().toLowerCase();
            System.out.print("Please enter Eligibility ");
            String second = input.nextLine().toLowerCase().trim();
            String sql = "INSERT INTO Location VALUES ('" + zero + "','" + first + "','" + second + "')";
            int number_row = stmt.executeUpdate(sql);
            if (number_row == 0) {
                System.out.println("There is no such a entry in database. Please try again. ");
                while (a >= 0) {
                    System.out.println("You can type " + a + " times more");
                    a--;
                    events(i);
                }
            } else {
                System.out.println(number_row + " row(s) affected! ");
                System.out.println("Location successfully added to Database");
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                String[] args = new String[0];
                main(args);
            } else
                events(12);
        } else if (i == 4) {
            System.out.print("--List of Location--\n");
            String search01 = "select id,name,eligibility from Location";
            ResultSet rhh = stmt.executeQuery(search01);
            while (rhh.next()) {
                String id = rhh.getString("id");
                String name = rhh.getString("name");
                String eligibility = rhh.getString("eligibility");
                System.out.format("%s\t%s\t%s\n", "Location Id: " + id, "---  Location Name: " + name, "---  Location Eligibility: " + eligibility);
            }
            System.out.print("----->> PLEASE ENTER REMOVING LOCATION ID: ");
            String zero = input.nextLine();
            String sql = "DELETE FROM Location WHERE (id = '" + zero + "')";
            int number_row = stmt.executeUpdate(sql);
            if (number_row == 0) {
                System.out.println("There is no such a entry in database. Please try again. ");
                while (a >= 0) {
                    System.out.println("You can type " + a + " times more");
                    a--;
                    events(i);
                }
            } else {
                System.out.println(number_row + " row(s) affected! ");
                System.out.println("Location successfully removed from Database");
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                String[] args = new String[0];
                main(args);
            } else
                events(12);
        } else if (i == 5) {
            String imsearch = "SELECT COUNT(*) eligibility FROM LOCATION";
            ResultSet rsk = stmt.executeQuery(imsearch);
            int num = 0;
            while (rsk.next()) {
                num = rsk.getInt("eligibility");
            }
            System.out.print("----List of events----\n");
            String search = "select distinct eligibility from location";
            ResultSet r1 = stmt.executeQuery(search);
            String[] list = new String[num];
            int x = 0;
            while (r1.next()) {
                String name_of_event = r1.getString("eligibility");
                System.out.print("--> ");
                System.out.format("%s\n", name_of_event + " <--");
                list[x] = r1.getString("eligibility").toLowerCase();
                x++;
            }
            System.out.print("--->>> PLEASE WRITE YOUR EVENT TYPE TO MAKE RESERVATION: ");
            String event_t = input.nextLine().toLowerCase().trim();
            int r = 0;
            for (int t = 0; t < x; t++) {
                if (list[t].equals(event_t)) {
                    r++;
                }
            }
            if (r == 0) {
                System.out.println("------Wrong Input. Type Again----- ");
                events(i);
            }
            String check0 = "SELECT name from Location where (eligibility = '" + event_t + "')";
            ResultSet r0 = stmt.executeQuery(check0);
            String[] loc_name2 = new String[4];
            int g = 0;
            while (r0.next()) {
                String name_of_place = r0.getString("name");
                System.out.print("--> Location: ");
                System.out.format("%s\t\n", name_of_place);
                loc_name2[g] = r0.getString("name").toLowerCase();
                g++;
            }

            System.out.print("Please enter Location Name: ");
            String loc_name = input.nextLine().toLowerCase().trim();
            int p = 0;
            for (int k = 0; k < g; k++) {
                if (loc_name2[k].equals(loc_name))
                    p++;
            }
            if (p == 0) {
                System.out.println("----Wrong Location Name. Enter again----");
                events(i);
            }
            System.out.print("Please enter Rezervation ID: ");
            String rez_id = input.nextLine().trim();
            System.out.print("Please enter Organizer Name: ");
            String organizer = input.nextLine().trim();
            System.out.print("Please enter Rezervation Name: ");
            String rez_name = input.nextLine().trim();
            System.out.print("Please enter Rezervation Day dd/mm/year : ");
            String rez_day = input.nextLine();
            System.out.print("Please enter Rezervation Start Time Between 0 and 24: ");
            int rez_start = input.nextInt();
            if (!(rez_start >= 0 && rez_start <= 24)) {
                System.out.println("Wrong input! Start Time should be Between 0 and 24. Please enter again");
                while (a >= 0) {
                    a--;
                    events(i);
                }
            }
            if ((rez_start >= 0 && rez_start <= 24) == true) {
                System.out.print("Please enter Rezervation End: ");
                int rez_end = input.nextInt();
                if (!(rez_end > rez_start && rez_end <= 24)) {
                    System.out.println("Wrong input! End Time should be greater than start time. Please enter again");
                    while (a >= 0) {
                        a--;
                        events(i);
                    }
                }

                String check = "SELECT count(*) rez_location from rezervation where (day = 'rez_day' and start = 'rez_start' and end = 'rez_end')";
                ResultSet rs3 = stmt.executeQuery(check);
                int count = 0;
                while (rs3.next()) {
                    count = rs3.getInt("rez_location");

                }
                if (count > 0) {
                    System.out.print("Sorry, There are some other events for this location.\nPlease to make new reservation PRESS 1 or to exit PRESS ANY OTHER NUMBER. ");
                    int h = input.nextInt();
                    if (h == 1) {
                        events(i);
                    } else {
                        return;
                    }
                }
                String sql = "INSERT INTO `rezervation` (`rezervation_id`, `rez_name`, `day`, `start`, `end`, `rez_location`, `organizer`, `event_type`) VALUES ('" + rez_id + "','" + rez_name + "','" + rez_day + "','" + rez_start + "','" + rez_end + "','" + loc_name + "','" + organizer + "','" + event_t + "')";
                int number_row = stmt.executeUpdate(sql);
                if (number_row == 0) {
                    System.out.println("Rezervation failed. Please try again. ");
                    while (a >= 0) {
                        System.out.println("You can type " + a + " times more");
                        a--;
                        events(i);
                    }
                } else {
                    System.out.println(number_row + " row(s) affected! ");
                    System.out.println("Rezervation successfully done! ");
                }

            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                String[] args = new String[0];
                main(args);
            } else
                events(12);
        } else if (i == 6) {
            System.out.println("\nList of Reservations: \n");
            String sqll = "SELECT * FROM Rezervation";
            ResultSet rsk = stmt.executeQuery(sqll);
            while (rsk.next()) {
                int rez_id = rsk.getInt("rezervation_id");
                String Rez_name = rsk.getString("rez_name");
                String day = rsk.getString("day");
                String type_ = rsk.getString("event_type");
                int start = rsk.getInt("start");
                int end = rsk.getInt("end");
                String rez_location = rsk.getString("rez_location");
                String organizer = rsk.getString("organizer");

                System.out.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", "Rez_id: " + rez_id, "Rez_name: " + Rez_name, "Day: " + day, "Start: " + start, "End: " + end, "Rez_Location: " + rez_location, "Organizer: " + organizer, "Event_Type:" + type_);
            }
            System.out.print("====>>>  PLEASE ENTER CANCELING RESERVATION ID: ");
            String zero = input.nextLine();
            String sql = "DELETE FROM Rezervation WHERE (rezervation_id = '" + zero + "')";
            int number_row = stmt.executeUpdate(sql);
            if (number_row == 0) {
                System.out.println("Rezervation failed. Please try again. ");
                while (a >= 0) {
                    System.out.println("You can type " + a + " times more");
                    a--;
                    events(i);
                }
            } else {
                System.out.println(number_row + " row(s) affected! ");
                System.out.println("Rezervation canceled successfully");
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                String[] args = new String[0];
                main(args);
            } else
                events(12);
        } else if (i == 7) {
            System.out.println("List of Whole Reservations: \n");
            String sql = "SELECT * FROM Rezervation";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int rez_id = rs.getInt("rezervation_id");
                String Rez_name = rs.getString("rez_name");
                String day = rs.getString("day");
                String event_ty = rs.getString("event_type");
                int start = rs.getInt("start");
                int end = rs.getInt("end");
                String rez_location = rs.getString("rez_location");
                String organizer = rs.getString("organizer");

                System.out.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n", "Rez_id: " + rez_id, "Rez_name: " + Rez_name, "Day: " + day, "Start: " + start, "End: " + end, "Rez_Location: " + rez_location, "Organizer: " + organizer, "Event_Type:" + event_ty);
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                String[] args = new String[0];
                main(args);
            } else
                events(12);
        } else if (i == 8) {
            System.out.print("List all reservations of a Location\n");
            String sql0 = "SELECT distinct rez_location FROM Rezervation";
            ResultSet rss = stmt.executeQuery(sql0);
            while (rss.next()) {
                String rez_location = rss.getString("rez_location");
                System.out.format("%s\t\n", "Rez_Location: " + rez_location);
            }
            System.out.print("---->>> PLEASE WRITE A LOCATION NAME: ");
            String location = input.nextLine().toLowerCase().trim();
            String sql = "SELECT rez_name FROM Rezervation WHERE (rez_location = '" + location + "') ";
            ResultSet rs2 = stmt.executeQuery(sql);
            while (rs2.next()) {
                String rez_location = rs2.getString("rez_name");
                System.out.format("%s\n", "Rez_Location:\t" + rez_location);
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                String[] args = new String[0];
                main(args);
            } else
                events(12);
        } else if (i == 9) {
            System.out.println("List all reservations of a Organizer: ");
            String sql00 = "SELECT distinct organizer FROM Rezervation";
            ResultSet rsss = stmt.executeQuery(sql00);
            while (rsss.next()) {
                String organizer = rsss.getString("organizer");
                System.out.format("%s\t\n", "Organizer Name: " + organizer);
            }
            System.out.print("--->>>PLEASE WRITE A ORGANIZER NAME: ");
            String organizer = input.nextLine().toLowerCase().trim();
            String sql = "SELECT rez_name FROM Rezervation WHERE ( organizer = '" + organizer + "')";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String rez_name_ = rs.getString("rez_name");
                System.out.format("%s\n", "Reservation name: " + rez_name_);
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                String[] args = new String[0];
                main(args);
            } else
                events(12);
        } else if (i == 10) {
            System.out.print("--List of Reservation--\n");
            String search001 = "select rez_name from Rezervation";
            ResultSet rhhh = stmt.executeQuery(search001);
            while (rhhh.next()) {
                String name = rhhh.getString("rez_name");
                System.out.format("%s\t\n", "Name: " + name);
            }
            System.out.print("PLEASE WRITE REZERVATION NAME TO SEE Organizer of : ");
            String rez_name_ = input.nextLine();
            String sql = "SELECT organizer FROM Rezervation WHERE (rez_name = '" + rez_name_ + "')";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String organizer = rs.getString("organizer");
                System.out.format("%s\n", "Organizer: " + organizer);
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                String[] args = new String[0];
                main(args);
            } else
                events(12);
        } else if (i == 11) {
            System.out.print("List all reservations of a Particular Event Type: ");
            String search002 = "select distinct event_type from Rezervation";
            ResultSet l = stmt.executeQuery(search002);
            while (l.next()) {
                String event_type__ = l.getString("event_type");
                System.out.format("%s\t\n", "Event_Type: " + event_type__);
            }
            System.out.print("===>>> Please write Event name: ");
            String event_name = input.nextLine().toLowerCase().trim();
            String sql = "SELECT * FROM Rezervation WHERE (event_type = '" + event_name + "')";
            ResultSet rs1 = stmt.executeQuery(sql);
            while (rs1.next()) {
                String Rez_id = rs1.getString("rezervation_id");
                String Rez_name = rs1.getString("rez_name");
                int day = rs1.getInt("day");
                int start = rs1.getInt("start");
                int end = rs1.getInt("end");
                String rez_location = rs1.getString("rez_location");
                String organizer = rs1.getString("organizer");
                System.out.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\n", "Rez_id: " + Rez_id, "Rez_name: " + Rez_name, "Day: " + day, "Start: " + start, "End: " + end, "Rez_Location: " + rez_location, "Organizer: " + organizer);
            }
            System.out.println("-> To see main list again please enter 1 or enter random number to exit.");
            int number = input.nextInt();
            if (number == 1) {
                String[] args = new String[0];
                main(args);
            } else
                events(12);
        } else if (i == 12) {
            stmt.close();
            conn.close();
            System.out.println("-----GoodBye----");
        }
    }

    public boolean deletelocation(int id) {
        boolean success = true;
        try {
            pstmt = conn.prepareStatement("DELET" +
                    "E FROM Location WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public static void main(String[] args) throws SQLException {
        Database ex = new Database();
        ex.init();
        boolean trial = ex.deletelocation(1001);
        if (trial = false)
            System.out.println(trial);
        System.out.println("1. Add Organizer\n" +
                "2. Remove Organizer\n" +
                "3. Add Location\n" +
                "4. Remove Location\n" +
                "5. Make reservation\n" +
                "6. Cancel reservation\n" +
                "7. List all reservations\n" +
                "8. List all reservations of a Location\n" +
                "9. List all reservations of an Organizer\n" +
                "10.Show Organizer of a particular reservation\n" +
                "11.List all reservations of a Particular Event Type(Concert, Meeting or Sports Organization)\n" +
                "12.Exit");
        System.out.print("Please choose any number from list: ");
        Scanner anything = new Scanner(System.in);
        int i = anything.nextInt();
        System.out.println("Number entered is : " + i);

        if (i >= 1 && i <= 12) {
            events(i);
        } else
            System.out.print("Please enter number between 1 and 12");
        int s = anything.nextInt();
        while ((s >= 1 && s <= 12) == false) {
            System.out.print("Please enter number between 1 and 12");
            int b = anything.nextInt();
            s = b;
            if (s >= 1 && s <= 12) {
                events(s);
            }
        }
    }
}