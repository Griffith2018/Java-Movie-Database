package zackgriffithlab12;
//Movie Database
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.*;
import javax.swing.border.*;

class MovieDatabase extends JFrame{
// JPanel for user inputs
   private JPanel inputMovieJPanel;
   
   // JLabel and JTextField for movie name
   private JLabel movieNameJLabel;
   private JTextField movieNameJTextField;
   
   // JLabel and JTextField for director
   private JLabel directorJLabel;
   private JTextField directorJTextField;

   // JLabel and JTextField for release year
   private JLabel releaseYearJLabel;
   private JTextField releaseYearJTextField;

   // JButton to add movie
   private JButton addMovieJButton;

  // JLabel and JTextArea to display movies
   private JLabel displayJLabel;
   private JTextArea displayJTextArea;
   
   // JButton to show movies in JTextArea
   private JButton showMovieJButton;
   
   //Linked list
   LinkedList<Movie> movies = new LinkedList<Movie>();
   
   // no-argument constructor
   public MovieDatabase()
   {
      createUserInterface();
   }

   // create and position GUI components; register event handlers
   private void createUserInterface()
   {
      // get content pane for attaching GUI components
      Container contentPane = getContentPane();

      // enable explicit positioning of GUI components 
      contentPane.setLayout( null );

      // set up inputMovieJPanel
      inputMovieJPanel = new JPanel();
      inputMovieJPanel.setBounds( 16, 16, 208, 218 );
      inputMovieJPanel.setBorder(
         new TitledBorder( "Input Movie" ) );
      inputMovieJPanel.setLayout( null );
      contentPane.add( inputMovieJPanel );
      
      // set up movieNameJLabel
      movieNameJLabel = new JLabel();
      movieNameJLabel.setBounds( 8, 30, 90, 23 );
      movieNameJLabel.setText( "Movie Name:" );
      inputMovieJPanel.add( movieNameJLabel );

      // set up movieNameJTextField
      movieNameJTextField = new JTextField();
      movieNameJTextField.setBounds( 104, 30, 88, 21 );
      movieNameJTextField.setHorizontalAlignment(
         JTextField.RIGHT );
      inputMovieJPanel.add( movieNameJTextField );
      
      // set up directorJLabel
      directorJLabel = new JLabel();
      directorJLabel.setBounds( 8, 60, 60, 23 );
      directorJLabel.setText( "Director:" );
      inputMovieJPanel.add( directorJLabel );

      // set up directorJTextField
      directorJTextField = new JTextField();
      directorJTextField.setBounds( 104, 60, 88, 21 );
      directorJTextField.setHorizontalAlignment( JTextField.RIGHT );
      inputMovieJPanel.add( directorJTextField );

      // set up releaseYearJLabel
      releaseYearJLabel = new JLabel();
      releaseYearJLabel.setBounds( 8, 90, 80, 23 );
      releaseYearJLabel.setText( "Release Year" );
      inputMovieJPanel.add( releaseYearJLabel );

      // set up releaseYearJTextField
      releaseYearJTextField = new JTextField();
      releaseYearJTextField.setBounds( 104, 90, 56, 21 );
      releaseYearJTextField.setHorizontalAlignment( JTextField.RIGHT );
      inputMovieJPanel.add( releaseYearJTextField );

      // set up addMovieJButton
      addMovieJButton = new JButton();
      addMovieJButton.setBounds( 72, 182, 120, 24 );
      addMovieJButton.setText( "Add Movie" );
      inputMovieJPanel.add( addMovieJButton );
      addMovieJButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    addMovieJButtonActionPerformed(event);
                }
            }
        );
      
      // set up displayJLabel
      displayJLabel = new JLabel();
      displayJLabel.setBounds( 240, 16, 150, 23 );
      displayJLabel.setText( "Movies:" );
      contentPane.add( displayJLabel );
      
      // set up displayJTextArea
      displayJTextArea = new JTextArea();
      displayJTextArea.setBounds( 240, 48, 402, 184 );
      displayJTextArea.setEditable( false );
      contentPane.add( displayJTextArea );
      
      // setup showMoviesJButton
      showMovieJButton = new JButton();
      showMovieJButton.setBounds( 500, 240, 120, 24 );
      showMovieJButton.setText( "Show Movies" );
      contentPane.add( showMovieJButton );
      showMovieJButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    showMovieJButtonActionPerformed(event);
                }
            }
        );
           
      // set properties of application's window
      setTitle( "Movies" ); // set title bar string
      setSize( 670, 308 );          // set window size
      setVisible( true );           // display window
   } // end method createUserInterface
   
   //button methods
   //add movie
   private void addMovieJButtonActionPerformed(ActionEvent event) {
       //get user input
       String name = movieNameJTextField.getText();       
       String director = directorJTextField.getText();
       int year = Integer.parseInt(releaseYearJTextField.getText());
       
       //add to the LinkedList
       movies.add(new Movie(name, director, year));

       //clear text fields
       movieNameJTextField.setText("");
       directorJTextField.setText("");
       releaseYearJTextField.setText("");   
   }
   //show movies
   private void showMovieJButtonActionPerformed(ActionEvent event) {
       //using Collections library to sort LinkedList by year
       Collections.sort(movies, new YearCompare());
       //add a header to display in text area
       displayJTextArea.setText("Year\tTitle\n");
       //display list of movies by release year
       for (int i = 0; i < movies.size(); i++) {
           displayJTextArea.append(Integer.toString(movies.get(i).year)+"\t");
           displayJTextArea.append(movies.get(i).name+"\n");
       }
   }
} //end MovieDatabase class

public class ZackGriffithLab12 {
    public static void main(String[] args) {
        MovieDatabase application = new MovieDatabase();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
}

//Movie Object
class Movie{
    String name;
    String director;
    int year;

    public Movie(String n, String d, int y){
        name = n;
        director = d;
        year = y;
    }
    //needed for comparator (sorting)
    public int getYear() {
        return year;
    }
}

//comparator class (for sorting Movie LinkedList by year)
//Disclosure: I wanted the LinkedList to be sorted by year, and after research this
//            was what I was looking for. Didn't just copy the code, but researched 
//            how Collections library could be utilized.
class YearCompare implements Comparator<Movie>{
    @Override
    public int compare(Movie m1, Movie m2){
        if(m1.getYear() > m2.getYear())
            return 1;
        else
            return -1;
    }
}
