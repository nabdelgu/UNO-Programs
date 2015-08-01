require 'rails_helper'

RSpec.describe "rails/new", type: :view do
  before(:each) do
    assign(:rail, Rail.new(
      :generate => "MyString",
      :model => "MyString",
      :User => "MyString",
      :name => "MyString",
      :email => "MyString",
      :encrypted_password => "MyString",
      :salt => "MyString"
    ))
  end

  it "renders new rail form" do
    render

    assert_select "form[action=?][method=?]", rails_path, "post" do

      assert_select "input#rail_generate[name=?]", "rail[generate]"

      assert_select "input#rail_model[name=?]", "rail[model]"

      assert_select "input#rail_User[name=?]", "rail[User]"

      assert_select "input#rail_name[name=?]", "rail[name]"

      assert_select "input#rail_email[name=?]", "rail[email]"

      assert_select "input#rail_encrypted_password[name=?]", "rail[encrypted_password]"

      assert_select "input#rail_salt[name=?]", "rail[salt]"
    end
  end
end
