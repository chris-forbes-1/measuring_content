package com.cs408.forbes.chris.global.comms;

import com.cs408.forbes.chris.analysis.types.KeyWord_file;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import java.util.ArrayList;


/**
 * The Grapher class is responsible for drawing all the data onto bar graphs, this
 * makes comparison of search methodologies easier to understand.
 * @author cforbes2013 <christopher.forbes@strath.ac.uk>
 *
 */
public class Grapher extends ApplicationFrame {

	private static final long serialVersionUID = 1L;
	private static List<KeyWord_file> KWDList = new ArrayList<KeyWord_file>();
	private static CategoryDataset dataset;
	/**
	 * Generic constructor for the Grapher class takes one list of KeyWord_file and generates the dataset 
	 * from this
	 * @param x List of @see KeyWord_File 
	 */
	public Grapher(List<KeyWord_file> x) {
		super("Search Divisions on document key word density");
		DrawGraph(x);
		dataset = createDataset();
		ChartPanel chartpanel = new ChartPanel(createChart(dataset));
		chartpanel.setPreferredSize(new Dimension(500,270));
		setContentPane(chartpanel);
		
	}
	/**
	 * Over loaded constructor for grapher, essentially allows the building of graphs from the same search
	 * but on different datasets e.g. lemmatized datasets vs non lemmatized datasets 
	 * @param x List of @see KeyWord_file
	 * @param y List of @see KeyWord_file
	 */
	public Grapher(List<KeyWord_file> x , List<KeyWord_file> y)
	{
		super("Search Divisions on document key word density");
		DrawGraph(x);
		DrawGraph(y);
		 dataset = createDataset();
		ChartPanel chartpanel = new ChartPanel(createChart(dataset));
		chartpanel.setPreferredSize(new Dimension(500,270));
		setContentPane(chartpanel);
		
	}
	
	/**
	 * Allows you to add more data to each graph instead of having a secondly overloaded constructor
	 * @param moreData List of @see KeyWord_file
	 */
	public void AddToData(List<KeyWord_file> moreData){
		DrawGraph(moreData);
		createDataset();
		
	}

	/**
	 * This creates the actual categorized dataset code derived from @url http://www.vogella.com/articles/JFreeChart/article.html
	 * @return CategoryDataset returns the dataset required to be displayed
	 */
	private static CategoryDataset createDataset(){
		DefaultCategoryDataset DcD = new DefaultCategoryDataset();
		for(KeyWord_file kWf : KWDList)
		{
			DcD.addValue(kWf.getKeyword_density(),kWf.getSearch().toString(), kWf.getFileName());
		}
		return DcD;
	}
	/**
	 * Creates the entire dataset to be used by the createDataset() function
	 * @param x List of @see KeyWord_file
	 */
	public static void DrawGraph(List<KeyWord_file> x) {
		for (KeyWord_file KWF : x) {
			KWDList.add(new KeyWord_file(KWF.getFileName(), KWF.getSearch(),
					KWF.getKeyword_density()));
		}
	}

	/**
	 * Literally creates the entire graph
	 * @param CD CategoryDataset from the createDataset function
	 * @return Chart returns the graph to be displayed.
	 */
	private JFreeChart createChart(CategoryDataset CD){
		JFreeChart chart = ChartFactory.createBarChart(
				KWDList.get(0).getSearch().toString(),
				"Searches",
				"Keyword Density",
				CD,
				PlotOrientation.VERTICAL,
				true,
				true,
				false
				);
		
		chart.setBackgroundPaint(Color.white);
		
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setDomainGridlinePaint(Color.black);
		plot.setRangeGridlinePaint(Color.black);
		
		
		BarRenderer rendered = (BarRenderer)plot.getRenderer();
		rendered.setDrawBarOutline(false);
		
		
		GradientPaint g1 = new GradientPaint(0.0f, 0.0f, Color.BLUE, 0.0f, 0.0f, Color.LIGHT_GRAY);
		GradientPaint g2 = new GradientPaint(0.0f,0.0f, Color.green, 0.0f, 0.0f, Color.LIGHT_GRAY);
		GradientPaint g3 = new GradientPaint(0.0f, 0.0f, Color.yellow, 0.0f,0.0f,Color.LIGHT_GRAY);
		
		rendered.setSeriesPaint(0, g1);
		rendered.setSeriesPaint(1,g2);
		rendered.setSeriesPaint(2,g3);
		
		CategoryAxis CA = plot.getDomainAxis();
		CA.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6));
		return chart;
	}
}
