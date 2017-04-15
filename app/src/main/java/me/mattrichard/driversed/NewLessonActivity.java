package me.mattrichard.driversed;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

public class NewLessonActivity extends NavigationActivity
        implements EditLessonFragment.OnLessonSaveListener {

    @Override
    protected int getNavID() {
        return R.id.drawer_new_lesson;
    }

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
        editLessonFragment.setLesson(new Lesson());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        // TODO: Confirm cancellation of current lesson before creating new lesson
        // "Replace existing lesson?" "Yes" "No"

        if (true /*response is yes*/) {
            editLessonFragment.setLesson(new Lesson());
        }
    }
}
