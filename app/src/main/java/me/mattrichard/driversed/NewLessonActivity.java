package me.mattrichard.driversed;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class NewLessonActivity extends NavigationActivity
        implements EditLessonFragment.OnLessonSaveListener {

    private EditLessonFragment editLessonFragment;

    @Override
    protected void setInnerContentView(ViewGroup root) {
        getLayoutInflater().inflate(R.layout.content_new_lesson, root);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editLessonFragment =
                (EditLessonFragment) getSupportFragmentManager().findFragmentById(R.id.lesson_fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean ret = super.onCreateOptionsMenu(menu);
        // Hide 'new lesson' action when already in new lesson activity.
        menu.findItem(R.id.action_new_lesson).setVisible(false);
        return ret;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return super.onNavigationItemSelected(item);
    }

    @Override
    public void onLessonSave() {

    }
}