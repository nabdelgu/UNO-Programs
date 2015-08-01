class CreateCheckouts < ActiveRecord::Migration
  def change
    create_table :checkouts do |t|
      t.string :address
      t.string :city
      t.integer :zip
      t.integer :card_number

      t.timestamps null: false
    end
  end
end
