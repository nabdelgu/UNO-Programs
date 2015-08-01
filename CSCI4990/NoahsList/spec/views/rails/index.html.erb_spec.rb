require 'rails_helper'

RSpec.describe "rails/index", type: :view do
  before(:each) do
    assign(:rails, [
      Rail.create!(
        :generate => "Generate",
        :model => "Model",
        :User => "User",
        :name => "Name",
        :email => "Email",
        :encrypted_password => "Encrypted Password",
        :salt => "Salt"
      ),
      Rail.create!(
        :generate => "Generate",
        :model => "Model",
        :User => "User",
        :name => "Name",
        :email => "Email",
        :encrypted_password => "Encrypted Password",
        :salt => "Salt"
      )
    ])
  end

  it "renders a list of rails" do
    render
    assert_select "tr>td", :text => "Generate".to_s, :count => 2
    assert_select "tr>td", :text => "Model".to_s, :count => 2
    assert_select "tr>td", :text => "User".to_s, :count => 2
    assert_select "tr>td", :text => "Name".to_s, :count => 2
    assert_select "tr>td", :text => "Email".to_s, :count => 2
    assert_select "tr>td", :text => "Encrypted Password".to_s, :count => 2
    assert_select "tr>td", :text => "Salt".to_s, :count => 2
  end
end
