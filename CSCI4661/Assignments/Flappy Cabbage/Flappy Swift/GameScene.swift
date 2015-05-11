import SpriteKit
import AVFoundation



extension Float {
    static func clamp(min: CGFloat, max: CGFloat, value: CGFloat) -> CGFloat {
        if(value > max) {
            return max
        } else if(value < min) {
            return min
        } else {
            return value
        }
    }
    
    static func range(min: CGFloat, max: CGFloat) -> CGFloat {
        return CGFloat(Float(arc4random()) / 0xFFFFFFFF) * (max - min) + min
    }
}

extension CGFloat {
    func degrees_to_radians() -> CGFloat {
        return CGFloat(M_PI) * self / 180.0
    }
}

class GameScene: SKScene, SKPhysicsContactDelegate{
    
    
    // Score icloud
    var score = Int()
    let keyICloud = "icloudScore"
    var keyStore = NSUbiquitousKeyValueStore()
    var theValue = Int()
    
    //sound
    var flappySound: AVAudioPlayer!
    var collisionSound: AVAudioPlayer!
    var pointScored: AVAudioPlayer!
    var gameOverHighscore:UIView!
    
    // Bird
    var bird: SKSpriteNode!
    
    // Background
    var background: SKNode!
    let background_speed = 100.0
    
    
    var label_score: SKLabelNode!
    
    // Instructions
    var instructions: SKSpriteNode!
    
    // Pipe Origin
    let pipe_origin_x: CGFloat = 382.0
    
    // Floor height
    let floor_distance: CGFloat = 5
    
    // Time Values
    var delta = NSTimeInterval(0)
    var last_update_time = NSTimeInterval(0)
    
    // Physics Categories
    let FSBoundaryCategory: UInt32 = 1 << 0
    let FSPlayerCategory: UInt32   = 1 << 1
    let FSPipeCategory: UInt32     = 1 << 2
    let FSGapCategory: UInt32      = 1 << 3
    
    // Game States
    
    enum FSGameState: Int {
        case FSGameStateStarting
        case FSGameStatePlaying
        case FSGameStateEnded
    }
    
    var state = FSGameState.FSGameStateStarting
    
    func signUpForNotifications(){
        
        NSNotificationCenter.defaultCenter().addObserver(self,
            selector: "ubiquitousKeyValueStoreDidChange:",
            name: NSUbiquitousKeyValueStoreDidChangeExternallyNotification,
            object: keyStore)
    }
    
    
    
    
    
    
    override func didMoveToView(view: SKView) {
        
        
        
        signUpForNotifications()
        initSound()
        initWorld()
        initBackground()
        initBird()
        initHUD()
    }
    
    
    func initSound(){
        flappySound = AVAudioPlayer(contentsOfURL:
            NSURL(fileURLWithPath: NSBundle.mainBundle().pathForResource(
                "punch", ofType: "wav")!), error: nil)
        
        collisionSound = AVAudioPlayer(contentsOfURL:
            NSURL(fileURLWithPath: NSBundle.mainBundle().pathForResource(
                "Die", ofType: "wav")!), error: nil)
        
        pointScored = AVAudioPlayer(contentsOfURL:
            NSURL(fileURLWithPath: NSBundle.mainBundle().pathForResource(
                "Score", ofType: "wav")!), error: nil)

    }
    
    func initICloud(){
        keyStore = NSUbiquitousKeyValueStore()
        
        let value = keyStore.stringForKey(keyICloud)
        
        if let stringValue = value{
            
            
        }
        
        NSNotificationCenter.defaultCenter().addObserver(self,
            selector: "ubiquitousKeyValueStoreDidChange:",
            name: NSUbiquitousKeyValueStoreDidChangeExternallyNotification,
            object: keyStore)
    }
    
    
    func initScore(){
        
        
        if(NSUserDefaults.standardUserDefaults().objectForKey("score") != nil){
            
            
            
            theValue = NSUserDefaults.standardUserDefaults().objectForKey("score") as Int
            
            
            let alert = UIAlertView()
            alert.title = "Score"
            var highScore = "HighScore: " + "\(theValue) "
            var currentScore = "Curent Score: " + "\(score)"
            var scoreLabel = highScore + currentScore
            
            alert.message = scoreLabel
            
            
            if(score > theValue){
                
                //store locally
                NSUserDefaults.standardUserDefaults().setObject(score, forKey: "score")
                NSUserDefaults.standardUserDefaults().synchronize()
                println("updated")
                
                //store in icloud
                var keyStore = NSUbiquitousKeyValueStore()
                keyStore.setString(toString(score), forKey: "score")
                keyStore.synchronize()
                
                
                
                alert.show()
                
                
                let delay = 5.0 * Double(NSEC_PER_SEC)
                var time = dispatch_time(DISPATCH_TIME_NOW, Int64(delay))
                dispatch_after(time, dispatch_get_main_queue(), {
                    alert.dismissWithClickedButtonIndex(-1, animated: true)
                })
                
                
                
                
            }else{
                alert.show()
                
                let delay = 5.0 * Double(NSEC_PER_SEC)
                var time = dispatch_time(DISPATCH_TIME_NOW, Int64(delay))
                dispatch_after(time, dispatch_get_main_queue(), {
                    alert.dismissWithClickedButtonIndex(-1, animated: true)
                })
                
            }
        }else{
            NSUserDefaults.standardUserDefaults().setObject(score, forKey: "score")
            NSUserDefaults.standardUserDefaults().synchronize()
            println("updated: nil")
            
        }
    }
    
    
    
    
    
    func initWorld() {
        physicsWorld.contactDelegate = self
        physicsWorld.gravity = CGVector(dx: 0.0, dy: -5.0)
        physicsBody = SKPhysicsBody(edgeLoopFromRect: CGRect(x: 0.0, y: floor_distance, width: size.width, height: size.height - floor_distance))
        physicsBody?.categoryBitMask = FSBoundaryCategory
        physicsBody?.collisionBitMask = FSPlayerCategory
    }
    
    
    func initBird() {
        bird = SKSpriteNode(imageNamed: "cabbage2")
        bird.position = CGPoint(x: 100.0, y: CGRectGetMidY(frame))
        bird.physicsBody = SKPhysicsBody(circleOfRadius: bird.size.width / 2.5)
        bird.physicsBody?.categoryBitMask = FSPlayerCategory
        bird.physicsBody?.contactTestBitMask = FSPipeCategory | FSGapCategory | FSBoundaryCategory
        bird.physicsBody?.collisionBitMask = FSPipeCategory | FSBoundaryCategory
        bird.physicsBody?.affectedByGravity = false
        bird.physicsBody?.allowsRotation = false
        bird.physicsBody?.restitution = 0.0
        bird.zPosition = 50
        addChild(bird)
        
        let texture1 = SKTexture(imageNamed: "cabbage2")
        let texture2 = SKTexture(imageNamed: "cabbage2")
        let textures = [texture1, texture2]
        
        bird.runAction(SKAction.repeatActionForever(SKAction.animateWithTextures(textures, timePerFrame: 0.1)))
    }
    
    
    
    func initHUD() {
        label_score = SKLabelNode(fontNamed:"MarkerFelt-Wide")
        label_score.position = CGPoint(x: CGRectGetMidX(frame), y: CGRectGetMaxY(frame) - 100)
        label_score.text = "0"
        label_score.zPosition = 50
        self.addChild(label_score)
        
        instructions = SKSpriteNode(imageNamed: "TapToStart")
        instructions.position = CGPoint(x: CGRectGetMidX(frame), y: CGRectGetMidY(frame) - 10)
        instructions.zPosition = 50
        addChild(instructions)
    }
    
    func initBackground() {
        background = SKNode()
        addChild(background)
        
        for i in 0...2 {
            let tile = SKSpriteNode(imageNamed: "sky")
            tile.anchorPoint = CGPointZero
            tile.position = CGPoint(x: CGFloat(i) * 640.0, y: 0.0)
            tile.name = "sky"
            tile.zPosition = 20
            background.addChild(tile)
        }
    }
    
    func moveBackground() {
        let posX = -background_speed * delta
        background.position = CGPoint(x: background.position.x + CGFloat(posX), y: 0.0)
        background.enumerateChildNodesWithName("sky") { (node, stop) in
            let background_screen_position = self.background.convertPoint(node.position, toNode: self)
            if background_screen_position.x <= -node.frame.size.width {
                node.position = CGPoint(x: node.position.x + (node.frame.size.width * 2), y: node.position.y)
            }
        }
    }
    
    
    
    func initPipes() {
        let screenSize = UIScreen.mainScreen().bounds
        let isWideScreen = (screenSize.height > 480)
        let bottom = getPipeWithSize(CGSize(width: 62, height: Float.range(40, max: isWideScreen ? 360 : 280)), side: false)
        bottom.position = convertPoint(CGPoint(x: pipe_origin_x, y: CGRectGetMinY(frame) + bottom.size.height/2 + floor_distance), toNode: background)
        bottom.physicsBody = SKPhysicsBody(rectangleOfSize: bottom.size)
        bottom.physicsBody?.categoryBitMask = FSPipeCategory;
        bottom.physicsBody?.contactTestBitMask = FSPlayerCategory;
        bottom.physicsBody?.collisionBitMask = FSPlayerCategory;
        bottom.physicsBody?.dynamic = false
        bottom.zPosition = 20
        background.addChild(bottom)
        
        let threshold = SKSpriteNode(color: UIColor.clearColor(), size: CGSize(width: 10, height: 120))
        threshold.position = convertPoint(CGPoint(x: pipe_origin_x, y: floor_distance + bottom.size.height + threshold.size.height/2), toNode: background)
        threshold.physicsBody = SKPhysicsBody(rectangleOfSize: threshold.size)
        threshold.physicsBody?.categoryBitMask = FSGapCategory
        threshold.physicsBody?.contactTestBitMask = FSPlayerCategory
        threshold.physicsBody?.collisionBitMask = 0
        threshold.physicsBody?.dynamic = false
        threshold.zPosition = 20
        background.addChild(threshold)
        
        let topSize = size.height - bottom.size.height - threshold.size.height - floor_distance
        let top = getPipeWithSize(CGSize(width: 62, height: topSize), side: true)
        top.position = convertPoint(CGPoint(x: pipe_origin_x, y: CGRectGetMaxY(frame) - top.size.height/2), toNode: background)
        top.physicsBody = SKPhysicsBody(rectangleOfSize: top.size)
        top.physicsBody?.categoryBitMask = FSPipeCategory;
        top.physicsBody?.contactTestBitMask = FSPlayerCategory;
        top.physicsBody?.collisionBitMask = FSPlayerCategory;
        top.physicsBody?.dynamic = false
        top.zPosition = 20
        background.addChild(top)
        
    }
    
    func getPipeWithSize(size: CGSize, side: Bool) -> SKSpriteNode {
        let textureSize = CGRect(x: 0.0, y: 0.0, width: size.width, height: size.height)
        let backgroundCGImage = UIImage(named: "brick_wall")!.CGImage
        
        UIGraphicsBeginImageContext(size)
        let context = UIGraphicsGetCurrentContext()
        CGContextDrawTiledImage(context, textureSize, backgroundCGImage)
        let tiledBackground = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        
        let backgroundTexture = SKTexture(CGImage: tiledBackground.CGImage)
        let pipe = SKSpriteNode(texture: backgroundTexture)
        pipe.zPosition = 1
        
        return pipe
    }
    
    func gameOver() {
        state = .FSGameStateEnded
        bird.physicsBody?.categoryBitMask = 0
        bird.physicsBody?.collisionBitMask = FSBoundaryCategory
        initScore()
        
        var timer = NSTimer.scheduledTimerWithTimeInterval(4.0, target: self, selector: Selector("restartGame"), userInfo: nil, repeats: false)
    }
    
    func restartGame() {
        state = .FSGameStateStarting
        bird.removeFromParent()
        background.removeAllChildren()
        background.removeFromParent()
        instructions.hidden = false
        removeActionForKey("generator")
        
        score = 0
        label_score.text = "0"
        
        initBird()
        initBackground()
    }
    
    func didBeginContact(contact: SKPhysicsContact!) {
        let collision:UInt32 = (contact.bodyA.categoryBitMask | contact.bodyB.categoryBitMask)
        
        if collision == (FSPlayerCategory | FSGapCategory) {
            score++
            pointScored.play()
            label_score.text = "\(score)"
        }
        
        if collision == (FSPlayerCategory | FSPipeCategory) {
            collisionSound.play()
            gameOver()
        }
        
        if collision == (FSPlayerCategory | FSBoundaryCategory) {
            collisionSound.play()
            if bird.position.y < 150 {
                gameOver()
            }
        }
    }
    
    override func touchesBegan(touches: NSSet, withEvent event: UIEvent) {
        if state == .FSGameStateStarting {
            state = .FSGameStatePlaying
            flappySound.play()
            
            instructions.hidden = true
            bird.physicsBody?.affectedByGravity = true
            bird.physicsBody?.applyImpulse(CGVector(dx: 0, dy: 25))
            
            runAction(SKAction.repeatActionForever(SKAction.sequence([SKAction.waitForDuration(2.0), SKAction.runBlock {
                self.initPipes()
                }])), withKey: "generator")
            
        } else if state == .FSGameStatePlaying {
            flappySound.play()
            bird.physicsBody?.applyImpulse(CGVector(dx: 0, dy: 25))
        }
    }
    
    override func update(currentTime: CFTimeInterval) {
        delta = (last_update_time == 0.0) ? 0.0 : currentTime - last_update_time
        last_update_time = currentTime
        
        if state != .FSGameStateEnded {
            moveBackground()
            
            let velocity_x = bird.physicsBody?.velocity.dx
            let velocity_y = bird.physicsBody?.velocity.dy
            
            if bird.physicsBody?.velocity.dy > 280 {
                bird.physicsBody?.velocity = CGVector(dx: velocity_x!, dy: 280)
            }
            
            bird.zRotation = Float.clamp(-1, max: 0.0, value: velocity_y! * (velocity_y < 0 ? 0.003 : 0.001))
        } else {
            bird.zRotation = CGFloat(M_PI)
            bird.removeAllActions()
        }
    }
    
}
                                                   