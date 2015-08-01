json.array!(@rails) do |rail|
  json.extract! rail, :id, :generate, :model, :User, :name, :email, :encrypted_password, :salt
  json.url rail_url(rail, format: :json)
end
