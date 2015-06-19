

import UIKit
import SpriteKit

class GameViewController: UIViewController {
    
    @IBOutlet var skView: SKView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        skView.showsFPS = true
        skView.showsNodeCount = true
        // skView.showsPhysics   = true
        
        if skView.scene == nil {
            let scene = GameScene(size: skView.bounds.size)
            skView.presentScene(scene)
        }
    }
    
    override func shouldAutorotate() -> Bool {
        return true
    }
    
    
    
    func ubiquitousKeyValueStoreDidChange(notification: NSNotification) {
        
        let storedString = GameScene().keyStore.stringForKey("score")
        NSUserDefaults.standardUserDefaults().setObject(storedString, forKey: "score")
        NSUserDefaults.standardUserDefaults().synchronize()
        
        
    }
    
    override func supportedInterfaceOrientations() -> Int {
        if UIDevice.currentDevice().userInterfaceIdiom == .Phone {
            return Int(UIInterfaceOrientationMask.AllButUpsideDown.rawValue)
        } else {
            return Int(UIInterfaceOrientationMask.All.rawValue)
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    override func prefersStatusBarHidden() -> Bool {
        return true
    }
}
