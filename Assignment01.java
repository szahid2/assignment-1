import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
// Sobia Zahid
// 02/22/2023
// Assignment 1
public class Assignment01 {

    public static String[] tags; // array to store the unique tags
    public static int[] countsOfTags;  // array to store the count of each tag

    public static void main(String[] args) {
        String file = "C:/Users/shyer/Downloads/tags.csv";
        String l;
        String csvSplit = ",";
        try (BufferedReader buff = new BufferedReader(new FileReader(file))) {
            // skipping header row
            buff.readLine(); // reading rows, updating the tag counts
            List<String> listTags = new ArrayList<>();
            while ((l = buff.readLine()) != null) {
                String[] y = l.split(csvSplit);
                String tag = y[2];
                if (!listTags.contains(tag)) {
                    listTags.add(tag);
                }
            }
            Collections.sort(listTags);  // sorting tags in alphabetical order
            tags = new String[listTags.size()];  // initializing tags array
            countsOfTags = new int[listTags.size()]; // initializing countsOfTags array
            for (int k = 0; k < listTags.size(); k++) {
                tags[k] = listTags.get(k);
            }
            buff.close();  // reset reader to start of file
            buff.readLine(); // skipping header row
            while ((l = buff.readLine()) != null) {
                String[] nums = l.split(csvSplit);
                String tag = nums[1];
                int index = Arrays.binarySearch(tags, tag);
                if (index >= 0) {
                    countsOfTags[index]++;
                }
            }
        } catch (IOException x) {
            x.printStackTrace();
        }

        // sorting tags by count in descending order
        List<String> listTags = new ArrayList<>(Arrays.asList(tags));
        listTags.sort((desc1, desc2) -> {         //ArrayList used for descending order
            int c01 = countsOfTags[Arrays.binarySearch(tags, desc1)];
            int c02 = countsOfTags[Arrays.binarySearch(tags, desc2)];
            return Integer.compare(c01, c02);
        });
        listTags.sort((asc1, asc2) -> {
            int c1 = countsOfTags[Arrays.binarySearch(tags, asc1)];
            int c2 = countsOfTags[Arrays.binarySearch(tags, asc2)];
            return Integer.compare(c1, c2);    // sorting tags by count in ascending order
        });
        // finding the three most popular movies from data
        System.out.println("Highest 3 movies by count: ");
        for (int n = 0; n < Math.min(listTags.size(), 3); n++) { // 3 most popular tags
            String top = listTags.get(n);  // using get as mentioned in assignment
            int numCount1 = countsOfTags[Arrays.binarySearch(tags, top)];
            System.out.println(top + ": " + numCount1);
        }

        // finding the three least popular movies from data
        System.out.println("\nLowest 3 movies by count: ");
        for (int m = 0; m < Math.min(listTags.size(), 3); m++) {
            String low = listTags.get(m); //using get as mentioned in assignment
            int numCount2 = countsOfTags[Arrays.binarySearch(tags, low)];
            System.out.println(low + ": " + numCount2);
        }

        Scanner scanner = new Scanner(System.in);
        while (true) {  // finding tags by count/name
            System.out.println("\nYou can search for the following: \nTag count by name" +
                    "\nTag name by count \nOr, you can exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter tag name: ");  // first option, to search by tag
                    String tagName = scanner.next();
                    int index = Arrays.binarySearch(tags, tagName);
                    if (index >= 0) {
                        System.out.println("Count for tag " + tagName + " is " + countsOfTags[index]);
                    } else {
                        System.out.println("That tag does not exist.");
                    }
                    break;
                case 2:
                    System.out.println("Enter tag count: "); // second option, to search by number of time tag shows up
                    int tagCount = scanner.nextInt();
                    System.out.println("Tags with count " + tagCount + ":");
                    for (int i = 0; i < tags.length; i++) {
                        if (countsOfTags[i] == tagCount) {
                            System.out.println(tags[i]);
                        }
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Try again, that was invalid."); // if it does not work
            }
        }

    }

}