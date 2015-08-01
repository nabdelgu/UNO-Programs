class Checkout < ActiveRecord::Base
  has_one :user

  VALID_CHECKOUT_REGEX = /\d{1,5}\s\w.\s(\b\w*\b\s){1,2}\w*\./i

  validates_presence_of :address, presence: true, length: { maximum: 50 }
  validates_presence_of :city, presence: true, length: { maximum: 30 }
  validates_presence_of :zip, presence: true, length: { minimum: 5},length:{maximum: 5},numericality: { only_integer: true }

end
