package views;

import model.*;
import views.IView;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.*;


/**
 * This class implements a GUI view of the shape photo album.
 */
public class SwingView extends JFrame implements IView {
  private String title;
  private IPhotoAlbumModel model;
  private JPanel IDPanel;
  private JLabel label;
  private GraphicPanel graphicPanel;
  private buttonPanel buttons = new buttonPanel();
  private int xMax = 1000;
  private int yMax = 1000;

  /**
   * Constructor.
   * @param title title of the view window
   * @param xMax width of the window
   * @param yMax height of the window
   */
  public SwingView(String title, int xMax, int yMax) {
    //super(title);
    this.title = title;
    if (xMax > 0 && yMax > 0) {
      this.xMax = xMax;
      this.yMax = yMax;
    }

    //createGUI();
  }

  /**
   * Create and show GUI view.
   * @param model photo album model instance
   */
  public void setUpGUI(IPhotoAlbumModel model) {
    this.setTitle(title);
    this.model = model;
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setPreferredSize(new Dimension(this.xMax,this.yMax));

    IDPanel = new JPanel(); //create id panel
    IDPanel.setBackground(Color.orange); //set background color of id panel

    graphicPanel = new GraphicPanel(model,IDPanel); //create graphic panel to display snapshot
    JScrollPane scrollPane = new JScrollPane(graphicPanel);

    scrollPane.setBackground(Color.cyan);
    //scrollPane.setOpaque(false);
    //graphicPanel.setOpaque(false);
    //create button panel

    this.getContentPane().add(IDPanel,BorderLayout.PAGE_START);
    this.getContentPane().add(buttons,BorderLayout.PAGE_END);
    this.getContentPane().add(scrollPane,BorderLayout.CENTER);

    pack();
    this.setLocationRelativeTo(null); //appear in the middle
    this.setVisible(true);
  }

  @Override
  public void registerActionListener(ActionListener listener) {
    buttons.setActionListener(listener);
  }

  @Override
  public void display(IPhotoAlbumModel model) {
    setUpGUI(model);
  }

  @Override
  public void repaint() {
    graphicPanel.repaint();
  }
}

/**
 * This class represents a button panel to display buttons.
 */
class buttonPanel extends JPanel {
  private JFrame frame;
  private JButton previous;
  private JButton next;
  private JButton select;
  private JButton quit;

  /**
   * Constructor.
   */
  public buttonPanel() {

    previous = new JButton("<< Prev <<");
    next = new JButton(">> Next >>");
    select = new JButton("^^ Select ^^");
    quit = new JButton("xx Quit xx ");
    previous.setActionCommand("previous");
    next.setActionCommand("next");
    select.setActionCommand("select");
    quit.setActionCommand("quit");

    this.setBackground(Color.orange);
    this.setLayout(new FlowLayout(FlowLayout.CENTER));

    this.add(previous);
    this.add(select);
    this.add(next);
    this.add(quit);
    this.setVisible(true);


  }

  /**
   * Add action listener to buttons in the button panel.
   * @param listener action listener
   */
  public void setActionListener(ActionListener listener) {
    previous.addActionListener(listener);
    next.addActionListener(listener);
    select.addActionListener(listener);
    quit.addActionListener(listener);

  }

}

/**
 * This class represents a graphic panel to display snapshots.
 */
class GraphicPanel extends JPanel {
  private IPhotoAlbumModel model;
  private JPanel IDPanel;
  private JLabel label;

  /**
   * Constructor.
   * @param model photo album model instance
   * @param IDPanel a panel to add ID and description
   */
  public GraphicPanel(IPhotoAlbumModel model, JPanel IDPanel) {
    this.model = model;

    this.IDPanel = IDPanel;
    label = new JLabel();
    label.setFont(new Font("Verdana", Font.ITALIC, 12));
    IDPanel.add(label);

    this.setBorder(BorderFactory.createLineBorder(Color.orange,3));
    this.setBackground(Color.CYAN);

  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(800,800);
  }

  @Override
  public void paintComponent(Graphics g) {
    // display single snapShot according to current index
    Graphics2D g2D = (Graphics2D) g;
    int index = model.getIndex();
    java.util.List<String> ids = model.getSnapshotIDs();
    String currentID = ids.get(index);
    ISnapshot snapshot = model.getCurrentSnapshot();
    java.util.List<IShape> shapes = snapshot.getShapes();
    String description = snapshot.getDescription();
    if (!Objects.equals(description, "")) {
      label.setText("<html>" + currentID + "<br>"
              + description );
    } else {
      label.setText(currentID);
    }

    for (IShape shape : shapes) {
      model.Color color = shape.getColor();
      java.awt.Color awtColor = new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue());
      g2D.setPaint(awtColor);
      if (shape instanceof Oval) {
        g2D.fillOval(shape.getX(), shape.getY(),
                shape.getWidth(), shape.getHeight());
      }
      if (shape instanceof model.Rectangle) {
        g2D.fillRect(shape.getX(), shape.getY(),
                shape.getWidth(), shape.getHeight());
      }
    }
  }

}

