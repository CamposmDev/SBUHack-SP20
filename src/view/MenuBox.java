package view;

import javafx.application.Platform;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MenuBox {
	private MenuBar menuBar;
	private Menu menuFile, menuView, menuHelp;
	private MenuItem miExit, miConfirmedCases, miDeaths, miRecoveries, miMichaelGH, 
	miKieferGH, miChrisGH, miJonathanGH, miCDC, miDonate, miSources, miAbout;
	private LineChart chartConfirmed, chartDeath, chartRecovery;
	private String[] urls;
	private Alert popup;
	
	public MenuBox(BorderPane pane) {
		menuBar = new MenuBar();		
		menuFile = new Menu("File");
		miExit = new MenuItem("Exit");
		
		menuView = new Menu("View");
		miConfirmedCases = new MenuItem("Confirmed Cases");
		miDeaths = new MenuItem("Deaths");
		miRecoveries = new MenuItem("Recoveries");
		
		menuHelp = new Menu("Help");
		miMichaelGH = new MenuItem("Michael's GitHub");
		miKieferGH = new MenuItem("Kiefer's GitHub");
		miChrisGH = new MenuItem("Chris's GitHub");
		miJonathanGH = new MenuItem("Jonathan's GitHub");
		miCDC = new MenuItem("CDC");
		miDonate = new MenuItem("Donate");
		miSources = new MenuItem("Sources");
		miAbout = new MenuItem("About");
		
		urls = new String[] {"https://www.github.com/Camposm97", "https://www.github.com/kiefuh", 
				"https://www.github.com/The3FacesOfSteve", "https://www.github.com/LemusJ27", 
				"https://www.cdc.gov", 
				"https://www.stjude.org/give.html?sc_dcm=254910812&sc_cid=kwp75674&source_code=IIQ190721002&ef_id=EAIaIQobChMIh8G8k_HT5wIVibWzCh1pgQDmEAAYASAAEgLjCvD_BwE:G:s&s_kwcid=AL!4519!3!322242236868!b!!g!!%2Bdonate&gclid=EAIaIQobChMIh8G8k_HT5wIVibWzCh1pgQDmEAAYASAAEgLjCvD_BwE",
				"https://www.youtube.com/watch?v=dQw4w9WgXcQ"};
		//Last url will be updated when source links are available
		ExternalLink url = new ExternalLink();
		
		popup = new Alert(AlertType.INFORMATION);
		popup.setTitle("About INSERT_APP_NAME_HERE");
		popup.setHeaderText(null);
		
		pane.setTop(this.getRoot());
		
		menuFile.getItems().add(miExit);
		menuView.getItems().addAll(miConfirmedCases, new SeparatorMenuItem(),
				miDeaths, new SeparatorMenuItem(), miRecoveries, new SeparatorMenuItem(), 
				miDonate);
		menuHelp.getItems().addAll(miMichaelGH, new SeparatorMenuItem(), miKieferGH, 
				new SeparatorMenuItem(), miChrisGH, new SeparatorMenuItem(), miJonathanGH,
				new SeparatorMenuItem(), miCDC, new SeparatorMenuItem(), miSources, 
				new SeparatorMenuItem(), miAbout);
		
		menuBar.getMenus().addAll(menuFile, menuView, menuHelp);
		
		chartConfirmed = makeChart();
		chartDeath = makeChart();
		chartRecovery = makeChart();

		
		ConfirmedBox bigCBox = new ConfirmedBox(chartConfirmed);
		VBox confirmedBox = bigCBox.getConfirmedBox();
		DeathBox bigDBox = new DeathBox(chartDeath);
		VBox deathBox = bigDBox.getDeathBox();
		RecoveryBox bigRBox = new RecoveryBox(chartRecovery);
		VBox recoveryBox = bigRBox.getRecoveryBox();
		
		miExit.setOnAction(e -> {
			Platform.exit();
		});
		
		miConfirmedCases.setOnAction(e -> {
			pane.setCenter(confirmedBox);
		});
		
		miDeaths.setOnAction(e -> {
			pane.setCenter(deathBox);
		});
		
		miRecoveries.setOnAction(e -> {
			pane.setCenter(recoveryBox);
		});
		
		miMichaelGH.setOnAction(e -> {url.openLink(urls[0]);});
		
		miKieferGH.setOnAction(e -> {url.openLink(urls[1]);});
		
		miChrisGH.setOnAction(e -> {url.openLink(urls[2]);});
		
		miJonathanGH.setOnAction(e -> {url.openLink(urls[3]);});
		
		miCDC.setOnAction(e -> {url.openLink(urls[4]);});
		
		miDonate.setOnAction(e -> {url.openLink(urls[5]);});
		
		miSources.setOnAction(e -> {url.openLink(urls[6]);});
		
		miAbout.setOnAction(e -> {
			popup.setContentText("This application tracks a disease, Coronavirus in this "
					+ "case, and shows how effective it is at ending people.");
			popup.showAndWait();
		});
		
	}
	
	public MenuBar getRoot() {
		return menuBar;
	}
	
	public LineChart makeChart() {
		NumberAxis xAxis = new NumberAxis();
		xAxis.setLabel("Days");
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Cases");
		LineChart chart = new LineChart(xAxis, yAxis);
		return chart;
	}
	
}
