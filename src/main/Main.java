package main;

import javax.swing.JFrame;

import builders.TopBarBuilder;
import components.CanvasWrapper;
import components.Frame;
import components.TopBarOptions;

public class Main {

	public static void main(String[] args) {
		JFrame frame = Frame.getFrame();
		frame.setTitle("JavaDraw");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		
		CanvasWrapper canvas = CanvasWrapper.getInstance();

		TopBarBuilder builder = new TopBarBuilder();

		builder.addItem("File", item -> {
			item.createItem("Open Image", TopBarOptions::ImportImage);
			item.createItem("Save", TopBarOptions::ExportCanvas);
		});

		builder.addItem("Desafio", item -> {
			item.createItem("Detectar Moedas", TopBarOptions::DesafioMoedas);
		});

		frame.setJMenuBar(builder.build());
		frame.add(canvas);
		
		frame.setVisible(true);
	}
}
