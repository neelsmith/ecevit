# Ecevit journalism

A repository for work with OCR'ed texts of Ecevit's journalism, 1950-1960.

In the `ocr` directory, text files are grouped in subdirectories by year of publication.

Files are plain-text files stripped from the OCR source using the OS X `textutil` utility. The source files are *not* checked into this repository.  (The original OCR source files were in either .docx format or .rtf format.   OCR file names ending in .doc were actually in .rtf format.)

The `src` directory includes simple tools for tokenizing and generating histograms of the text files, written in scala.
