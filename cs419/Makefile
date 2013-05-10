TEXFILE = "Assignment2"
a1:
	pdflatex $(TEXFILE).tex
	rm *.log
	rm *.aux

a1-view:
	pdflatex $(TEXFILE).tex
	rm *.log
	rm *.aux
	evince $(TEXFILE).pdf

clean:
	rm *.log
	rm *.aux
	rm *.dvi
