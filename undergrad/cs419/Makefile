TEXFILE = "Assignment3"
FINAL="FinalReport"
a1:
	pdflatex $(TEXFILE).tex
	rm *.log
	rm *.aux

a1-view:
	pdflatex $(TEXFILE).tex
	rm *.log
	rm *.aux
	evince $(TEXFILE).pdf

final:
	pdflatex $(FINAL).tex
	rm *.log
	rm *.aux
	evince $(FINAL).pdf

clean:
	rm *.log
	rm *.aux
	rm *.dvi
