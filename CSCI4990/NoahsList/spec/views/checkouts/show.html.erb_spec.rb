require 'rails_helper'

RSpec.describe "checkouts/show", type: :view do
  before(:each) do
    @checkout = assign(:checkout, Checkout.create!(
      :address => "Address",
      :city => "City",
      :zip => 1,
      :card_number => 2
    ))
  end

  it "renders attributes in <p>" do
    render
    expect(rendered).to match(/Address/)
    expect(rendered).to match(/City/)
    expect(rendered).to match(/1/)
    expect(rendered).to match(/2/)
  end
end
