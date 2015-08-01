require 'rails_helper'

RSpec.describe "checkouts/index", type: :view do
  before(:each) do
    assign(:checkouts, [
      Checkout.create!(
        :address => "Address",
        :city => "City",
        :zip => 1,
        :card_number => 2
      ),
      Checkout.create!(
        :address => "Address",
        :city => "City",
        :zip => 1,
        :card_number => 2
      )
    ])
  end

  it "renders a list of checkouts" do
    render
    assert_select "tr>td", :text => "Address".to_s, :count => 2
    assert_select "tr>td", :text => "City".to_s, :count => 2
    assert_select "tr>td", :text => 1.to_s, :count => 2
    assert_select "tr>td", :text => 2.to_s, :count => 2
  end
end
