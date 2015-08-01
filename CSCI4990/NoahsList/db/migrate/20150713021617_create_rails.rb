class CreateRails < ActiveRecord::Migration
  def change
    create_table :rails do |t|
      t.string :generate
      t.string :model
      t.string :User
      t.string :name
      t.string :email
      t.string :encrypted_password
      t.string :salt

      t.timestamps null: false
    end
  end
end
