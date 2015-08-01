require 'rails_helper'

RSpec.describe "rails/show", type: :view do
  before(:each) do
    @rail = assign(:rail, Rail.create!(
      :generate => "Generate",
      :model => "Model",
      :User => "User",
      :name => "Name",
      :email => "Email",
      :encrypted_password => "Encrypted Password",
      :salt => "Salt"
    ))
  end

  it "renders attributes in <p>" do
    render
    expect(rendered).to match(/Generate/)
    expect(rendered).to match(/Model/)
    expect(rendered).to match(/User/)
    expect(rendered).to match(/Name/)
    expect(rendered).to match(/Email/)
    expect(rendered).to match(/Encrypted Password/)
    expect(rendered).to match(/Salt/)
  end
end
