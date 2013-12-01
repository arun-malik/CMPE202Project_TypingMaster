import greenfoot.World;

public class TypingMaster extends World
{
    public static final int GRID_SIZE               = 75;
    public static final int GRID_WIDTH              = 16;
    public static final int GRID_HEIGHT             = 9;
    
    public static final String DEFAULT_BACKGROUND   = "images/prep/background.jpg";
    public static final String BUTTON_PLAY          = "images/prep/start.png";  
    public static final String BUTTON_HELP          = "images/prep/help.png";
    public static final String BUTTON_EXIT          = "images/prep/exit.png";
    
    public static final String BUTTON_EASY          = "images/prep/easy.png";
    public static final String BUTTON_MEDIUM        = "images/prep/medium.png";
    public static final String BUTTON_HARD          = "images/prep/hard.png";
    
    public static final String BUTTON_SPACE         = "images/prep/space.png";
    public static final String BUTTON_UNDERWATER    = "images/prep/underwater.png";
    
    private Scene scene;
    private Theme theme;
    private Champion champion;
    private EnemyWatcher enemyWatcher;
    private ScoreKeeper scoreKeeper;
    private Difficulty difficuly;

    /**
     * Constructor for objects of class TypingGame.
     */
    public TypingMaster()
    {    
        // World has 16x9 cells with a cell size of 75x75 pixels.
        super(GRID_WIDTH, GRID_HEIGHT, GRID_SIZE);
        
        // Set Background
        setBackground("images/prep/background.jpg");
        getBackground().scale(getWidth() * getCellSize(), getHeight() * getCellSize());
    }
    
    public void started() {
        super.started();
        prepare();
    }
    
    public void stopped() {
        super.stopped();
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program. That is: create the initial
     * objects and add them to the world.
     */
    private void prepare() {
        // Set Background
        setBackground(DEFAULT_BACKGROUND);
        getBackground().scale(getWidth() * getCellSize(), getHeight() * getCellSize());
        
        // Default Settings: Difficulty: Easy and Theme: Space
        difficuly = new Easy();
        theme = new Space();
        champion = theme.getChampion();

        // Create Scenes
        setScene(getMainMenu());

    }
    
    /**
     * Returns the Selected Theme
     * @return Returns the Selected Theme
     */
    public Theme getTheme() {
        return theme;
    }
    
    /**
     * Set the current theme
     * @param theme game theme
     */
    public void setTheme(Theme theme) {
        this.theme = theme;
        
        if(this.theme != null) { 
            champion = this.theme.getChampion();
        }
    }
    
    /**
     * Returns the Selected Champion
     * @return the champion for the selected theme
     */
    public Champion getChampion() {
        return champion;
    }
        
    /**
     * Returns the Current "Scene" State
     * @return Returns the Current "Scene" State
     */
    public Scene getScene() {
        return scene;
    }
    
    /**
     * Sets the "Scene" State
     * @param scene the new "Scene"
     */
    public void setScene(Scene scene) {
        if(this.scene != null) {
            this.scene.exitScene();
            removeObject(this.scene);
        }
        
        this.scene = scene;
        if(this.scene != null) {
            addObject(this.scene, -10, -10);
            this.scene.enterScene();
        }
    }
    
    /**
     * Returns Main Menu Scene
     * @return Returns Main Menu Scene
     */
    public MainMenu getMainMenu() {
        return new MainMenu();
    }
    
    /**
     * Returns the Game Screen Scene
     * @return Returns the Game Screen Scene
     */
    public GameScreen getGameScreen() {
        return new GameScreen();
    }
    
    /**
     * Returns the Help Screen Scene
     * @return Help Screen Scene
     */
    public HelpScreen getHelpScreen() {
        return new HelpScreen();
    }
    
    /**
     * Returns the Settings Screenprep
     * @return Returns the Settings Screen
     */
    public SettingsScreen getSettingsScreen() {
        return new SettingsScreen();
    }
    
    /**
     * Returns the Selected Difficulty
     * @return returns the current Difficulty
     */
    public Difficulty getDifficuly() {
        return difficuly;
    }
    
    /**
     * Set the difficult level
     * @param difficuly game difficulty level
     */
    public void setDifficuly(Difficulty difficuly) {
        this.difficuly = difficuly;
    }
    
    /**
     * Returns the EnemyWatcher
     * @return Returns the EnemyWatcher
     */
    public EnemyWatcher getEnemyWatcher() {
        return enemyWatcher;
    }
    
    /**
     * Set the Enemy Watcher 
     * @param enemyWatcher Set the Enemy Watcher
     */
    public void setEnemyWatcher(EnemyWatcher enemyWatcher) {
        this.enemyWatcher = enemyWatcher;
    }
        
    /**
     * Returns the current Score Keeper
     * @return current score keeper
     */
    public ScoreKeeper getScoreKeeper() {
        return scoreKeeper;
    }
    
    /**
     * Set the score keeper 
     * @param scoreKeeper the current score keeper
     */
    public void setScoreKeeper(ScoreKeeper scoreKeeper) {
        this.scoreKeeper = scoreKeeper;
    }
    
}
