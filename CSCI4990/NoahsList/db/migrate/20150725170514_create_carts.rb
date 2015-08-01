class CreateCarts < ActiveRecord::Migration
  def change
    create_table :carts do |t|
      t.string :email
      t.string :products

      t.timestamps null: false
    end
  end
end
