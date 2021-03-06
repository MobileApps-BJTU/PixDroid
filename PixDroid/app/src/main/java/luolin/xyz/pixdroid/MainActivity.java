package luolin.xyz.pixdroid;

        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.ActionBarActivity;
        import android.os.Bundle;
        import android.support.v7.app.ActionBarDrawerToggle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.support.v7.widget.Toolbar;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;


public class MainActivity extends ActionBarActivity {
    String TITLES[];
    int ICONS[] = {R.drawable.ic_home,R.drawable.ic_favorite,R.drawable.ic_archive,R.drawable.ic_trash,R.drawable.ic_settings,R.drawable.ic_help};

    String NAME = "Luo Lin";
    String EMAIL = "12301127@bjtu.edu.cn";
    int PROFILE = R.drawable.aka;

    private int lastChosenFlag = 1;
    private Toolbar toolbar;
    private HomeFragment homeFragment;
    private FavoriteFragment favoriteFragment;
    private ArchiveFragment archiveFragment;
    private TrashFragment trashFragment;


    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    MyAdapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TITLES = new String[]{
                getString(R.string.home),
                getString(R.string.favorite),
                getString(R.string.archive),
                getString(R.string.trash),
                getString(R.string.settings),
                getString(R.string.help)};
        toolbar = (Toolbar)findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView)findViewById(R.id.RecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.SetOnItemClickListener(adapterItemClickListener);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }
        };

        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        if(homeFragment == null){
            homeFragment = new HomeFragment();
        }
        //CardView
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_holder,homeFragment)
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() > 0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }

    public MyAdapter.OnItemClickListener adapterItemClickListener = new MyAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            mAdapter.setChosenFlag(position);
            mAdapter.notifyItemChanged(position);
            if(lastChosenFlag != 0){
                mAdapter.notifyItemChanged(lastChosenFlag);
            }
            lastChosenFlag = position;
            switch (position){
                case 1:

                    getSupportActionBar().setTitle(R.string.app_name);
                    if(homeFragment == null){
                        homeFragment = new HomeFragment();
                    }
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_holder, homeFragment)
                            .addToBackStack(null)
                            .commit();
                    Drawer.closeDrawers();
                    break;

                case 2:
                    getSupportActionBar().setTitle(R.string.favorite);
                    if(favoriteFragment == null){
                        favoriteFragment = new FavoriteFragment();
                    }
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_holder, favoriteFragment)
                            .addToBackStack(null)
                            .commit();
                    Drawer.closeDrawers();
                    break;

                case 3:
                    getSupportActionBar().setTitle(R.string.archive);
                    if(archiveFragment == null){
                        archiveFragment = new ArchiveFragment();
                    }
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_holder, archiveFragment)
                            .addToBackStack(null)
                            .commit();
                    Drawer.closeDrawers();
                    break;

                case 4:
                    getSupportActionBar().setTitle(R.string.trash);
                    if(trashFragment == null){
                        trashFragment = new TrashFragment();
                    }
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_holder,trashFragment)
                            .addToBackStack(null)
                            .commit();
                    Drawer.closeDrawers();
                    break;

                default:break;

            }
        }
    };
}
