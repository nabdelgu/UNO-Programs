require 'rails_helper'

RSpec.describe "checkouts/new", type: :view do
  before(:each) do
    assign(:checkout, Checkout.new(
      :address => "MyString",
      :city => "MyString",
      :zip => 1,
      :card_number => 1
    ))
  end

  it "renders new checkout form" do
    render

    assert_select "form[action=?][method=?]", checkouts_path, "post" do

      assert_select "input#checkout_address[name=?]", "checkout[address]"

      assert_select "input#checkout_city[name=?]", "checkout[city]"

      assert_select "input#checkout_zip[name=?]", "checkout[zip]"

      assert_select "input#checkout_card_number[name=?]", "checkout[card_number]"
    end
  end
end
