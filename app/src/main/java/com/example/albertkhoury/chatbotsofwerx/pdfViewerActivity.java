package com.example.albertkhoury.chatbotsofwerx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.shockwave.pdfium.PdfDocument;

import java.util.List;

import static com.example.albertkhoury.chatbotsofwerx.MainActivity.TAG;


//declared abstract due to no implementation of:
    // OnPageChangeListener AND OnLoadCompleteListener
    //as of now
public class pdfViewerActivity extends Activity implements OnPageChangeListener, OnLoadCompleteListener {

    private static final String TAG = pdfViewerActivity.class.getSimpleName();
    public static final String SAMPLE_FILE = "Ben Clark - Red Team Field Manual.pdf";
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    Button returnToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_viewer);

        pdfView= (PDFView)findViewById(R.id.pdfView);
        returnToSearch = (Button) findViewById(R.id.returnButton);
        displayFromAsset(SAMPLE_FILE);
    }

    private void displayFromAsset(String assetFileName) {
        pdfFileName = assetFileName;

        pdfView.fromAsset(SAMPLE_FILE)
                .defaultPage(pageNumber)
                .enableSwipe(true)

                .swipeHorizontal(false)
                .onPageChange(this)
                .enableAnnotationRendering(true)
                .onLoad(this)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }

    public void gotoMainActivity(View view) {
        if (view == returnToSearch) {
            Intent intentPdfViewer = new Intent(pdfViewerActivity.this, MainActivity.class);
            pdfViewerActivity.this.startActivity(intentPdfViewer);
        }
    }



    @Override
    public void onPageChanged(int page, int pageCount) {
        pageNumber = page;
        setTitle(String.format("%s %s / %s", pdfFileName, page + 1, pageCount));
    }

    @Override
    public void loadComplete(int nbPages) {
        PdfDocument.Meta meta = pdfView.getDocumentMeta();
        printBookmarksTree(pdfView.getTableOfContents(), "-");

    }

    public void printBookmarksTree(List<PdfDocument.Bookmark> tree, String sep) {
        for (PdfDocument.Bookmark b : tree) {

            Log.e(TAG, String.format("%s %s, p %d", sep, b.getTitle(), b.getPageIdx()));

            if (b.hasChildren()) {
                printBookmarksTree(b.getChildren(), sep + "-");
            }
        }
    }



}
