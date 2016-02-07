package ch.niceneasy.jd;

import java.io.File;
import java.io.FileWriter;

import jd.commonide.IdeDecompiler;
import jd.commonide.preferences.IdePreferences;

public class Main {

	public static void main(String[] args) {
		try {
			if (args.length < 2) {
				System.err.println("Usage: java -jar jd-compiler.jar <classfile> <outfile>");
				return;
			}
			String basePath = new File(args[0].trim()).getParent();
			String classFile = new File(args[0].trim()).getName();
			boolean showDefaultConstructor = true;
			boolean realignmentLineNumber = false;
			boolean showPrefixThis = false;
			boolean mergeEmptyLines = false;
			boolean unicodeEscape = false;
			boolean showLineNumbers = false;
			boolean showMetadata = false;
			IdePreferences preferences = new IdePreferences(showDefaultConstructor, realignmentLineNumber,
					showPrefixThis, mergeEmptyLines, unicodeEscape, showLineNumbers, showMetadata);
			File file = new File(args[1]);
			file.createNewFile();
			FileWriter writer = new FileWriter(file);
			writer.write(IdeDecompiler.decompile(preferences, basePath, classFile));
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
