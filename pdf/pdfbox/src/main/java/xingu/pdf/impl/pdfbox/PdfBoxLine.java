package xingu.pdf.impl.pdfbox;

import java.util.ArrayList;
import java.util.List;

import xingu.lang.NotImplementedYet;
import xingu.pdf.Line;
import xingu.pdf.Word;
import xingu.pdf.impl.LineSupport;

public class PdfBoxLine
	extends LineSupport
{
	private String	text;
	
	private List<Word> words;

	public PdfBoxLine(int page, String text, int number)
	{
		this.page   = page;
		this.number = number;
		this.text   = text;
		this.words  = parse();
	}

	private List<Word> parse()
	{
		String[] parts = text.split(" ");
		List<Word> words = new ArrayList<>(parts.length);
		for(String w : parts)
		{
			words.add(new PdfBoxWord(page, w));
		}
		return words;
	}

	@Override
	public String text()
	{
		return text;
	}

	@Override
	public Word word(int i)
	{
		return words.get(i);
	}

	@Override
	public List<Word> words()
	{
		return words;
	}
}
