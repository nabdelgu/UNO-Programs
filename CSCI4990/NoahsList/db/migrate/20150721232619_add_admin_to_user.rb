class AddAdminToUser < ActiveRecord::Migration
  def change
    add_column :users, :adminUser, :boolean
  end
end
