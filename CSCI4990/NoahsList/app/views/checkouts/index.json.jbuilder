json.array!(@checkouts) do |checkout|
  json.extract! checkout, :id, :address, :city, :zip, :card_number
  json.url checkout_url(checkout, format: :json)
end
