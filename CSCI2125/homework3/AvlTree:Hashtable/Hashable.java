/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


    public interface Hashable
    {
        /**
         * Compute a hash function for this object.
         * @param tableSize the hash table size.
         * @return (deterministically) a number between
         *     0 and tableSize-1, distributed equitably.
         */
        int hash( int tableSize );
    }
