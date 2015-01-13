
    // Basic node stored in unbalanced binary search trees
    // Note that this class is not accessible outside
    // of package DataStructures

      public class BinaryNode<AnyType>{
        AnyType element;	// The data in the node
        BinaryNode<AnyType> left;  // Left Child
        BinaryNode<AnyType> right; // Right Child
        int height;
    
	// constructors
        BinaryNode( AnyType _element ) {
            this( _element, null, null, 0 );
        }

        BinaryNode( AnyType _element, BinaryNode<AnyType> _left, BinaryNode<AnyType> _right, int _height ) {
            element = _element;
            left = _left;
            right = _right;
            height = _height;
        }
    } 
    


